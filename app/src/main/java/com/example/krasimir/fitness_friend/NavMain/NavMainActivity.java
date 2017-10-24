package com.example.krasimir.fitness_friend.NavMain;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.krasimir.fitness_friend.Challanges.ListChallanges.ListChallangesActivity;
import com.example.krasimir.fitness_friend.Recipes.CreateRecipe.CreateRecipeActivity;
import com.example.krasimir.fitness_friend.Home.HomeFragment;
import com.example.krasimir.fitness_friend.Exercises.ListExercises.ListExercises.ListExercisesActivity;
import com.example.krasimir.fitness_friend.Recipes.ListRecipes.ListRecipesActivity;
import com.example.krasimir.fitness_friend.R;
import com.example.krasimir.fitness_friend.SignIn.SignInActivity;
import com.example.krasimir.fitness_friend.SignUp.SignUpActivity;
import com.example.krasimir.fitness_friend.UserProfile.UserProfileActivity;
import com.example.krasimir.fitness_friend.base.authentication.AuthenticationProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class NavMainActivity extends DaggerAppCompatActivity {

    private Fragment mFragment;
    private Toolbar mToolbar;
    private Drawer mDrawer;
    private DrawerBuilder mDrawerBuilder;
    private AccountHeader mDrawerHeader;
    private PrimaryDrawerItem mHomeDrawerItem;
    private PrimaryDrawerItem mSignInDrawerItem;
    private PrimaryDrawerItem mSignUpDrawerItem;
    private PrimaryDrawerItem mSignOutDrawerItem;
    private PrimaryDrawerItem mProfileDrawerItem;
    private PrimaryDrawerItem mCreateRecipeDrawerItem;
    private PrimaryDrawerItem mBrowseRecipesDrawerItem;
    private PrimaryDrawerItem mLocationDrawerItem;
    private PrimaryDrawerItem mExerciseDrawerItem;

    private static final int CAMERA_REQUEST = 1;
    private static final int REQUEST_WRITE_PERMISSION = 2;
    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 3;

    private File photoFile;
    private String mCurrentPhotoPath;

    @Inject
    AuthenticationProvider mAuthProvider;
    private Uri mImageUri;
    private PrimaryDrawerItem mChallangeDrawerItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_container, new HomeFragment())
                .commit();
    }

    protected void onStart() {
        setupDrawerHeaderProfileImage();
        addDrawerItems();
        addDrawer();

        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String uid = mAuthProvider.getUserId();
        String imageFileName = uid + ".jpg";
        File path = getExternalFilesDir("Pictures");

        try {
            if (!path.exists()) {
                path.mkdirs();
            }

            File imageFile = new File(path, imageFileName);

            mImageUri = FileProvider.getUriForFile(getApplicationContext(), "com.example.krasimir.fitness_friend.fileprovider", imageFile);

            takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
            startActivityForResult(takePictureIntent, CAMERA_REQUEST);
        } catch (Exception e) {
            Log.w("ExternalStorage", "Error writing ", e);
        }
    }

    private Uri getProfileImageUri() {
        String uid = mAuthProvider.getUserId();
        String imageFileName = uid + ".jpg";
        File path = getExternalFilesDir("Pictures");

        File imageFile = new File(path, imageFileName);

        Uri uri;

        if (!imageFile.exists()) {
            return null;
        } else {
            uri = FileProvider.getUriForFile(getApplicationContext(),
                    "com.example.krasimir.fitness_friend.fileprovider", imageFile);
        }

        return uri;
    }

    public void setupDrawerHeaderProfileImage() {
        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(final ImageView imageView,
                            final Uri uri,
                            final Drawable placeholder,
                            final String tag) {
                Glide.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Glide.clear(imageView);
            }

            @Override
            public Drawable placeholder(final Context ctx, final String tag) {
                if (DrawerImageLoader.Tags.PROFILE.name().equals(tag)) {
                    return DrawerUIUtils.getPlaceHolder(ctx);
                } else if (DrawerImageLoader.Tags.ACCOUNT_HEADER.name().equals(tag)) {
                    return new IconicsDrawable(ctx)
                            .iconText(" ")
                            .backgroundColorRes(com.mikepenz.materialdrawer.R.color.primary)
                            .sizeDp(56);
                }

                return super.placeholder(ctx, tag);
            }
        });
    }

    public void setupDrawerHeader(final FirebaseUser user) {
        final ProfileDrawerItem userProfile = new ProfileDrawerItem();

        if (user != null) {
            String userEmail = user.getEmail();
            String userName = user.getDisplayName();

            if (user.getPhotoUrl() != null) {
                boolean isTrue = user.getPhotoUrl() != null;
                Log.v("Profile image", Boolean.toString(isTrue));
                userProfile
                        .withName(userEmail).withTextColor(Color.BLACK)
                        .withEmail(userName).withTextColor(Color.BLACK)
                        .withIcon(user.getPhotoUrl().toString());
            } else if (getProfileImageUri() != null) {
                Log.v("PROFILE_PICTURE", getProfileImageUri().toString());
                userProfile
                        .withName(userEmail).withTextColor(Color.BLACK)
                        .withEmail(userName).withTextColor(Color.BLACK)
                        .withIcon(getProfileImageUri().toString());
            } else {
                userProfile
                        .withName(userEmail).withTextColor(Color.BLACK)
                        .withEmail(userName).withTextColor(Color.BLACK);
            }
        } else {
            userProfile
                    .withName("Not signed in").withTextColor(Color.BLACK)
                    .withEmail("Anonymous").withTextColor(Color.BLACK);
        }

        mDrawerHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.headerBackground)
                .addProfiles(userProfile)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        for (UserInfo user : mAuthProvider.getFirebaseInstance().getCurrentUser().getProviderData()) {
                            if (user.getProviderId().equals("password")) {
                                if (checkAndRequestPermissions()) {
                                    openCamera();
                                }
                            }
                        }

                        if (mImageUri != null) {
                            mDrawerHeader.updateProfile(userProfile.withIcon(mImageUri));
                        }

                        return false;
                    }
                })
                .build();
    }

    private boolean checkAndRequestPermissions() {
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }


    public void addDrawerItems() {

        mHomeDrawerItem =
                new PrimaryDrawerItem().withName(R.string.nav_home).withIdentifier(1)
                        .withIcon(GoogleMaterial.Icon.gmd_home)
                        .withIconColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));

        mProfileDrawerItem =
                new PrimaryDrawerItem().withName(R.string.nav_profile).withIdentifier(2)
                        .withIcon(GoogleMaterial.Icon.gmd_person)
                        .withIconColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));

        mSignInDrawerItem =
                new PrimaryDrawerItem().withName(R.string.nav_sign_in).withIdentifier(3)
                        .withIcon(FontAwesome.Icon.faw_sign_in)
                        .withIconColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));

        mSignUpDrawerItem =
                new PrimaryDrawerItem().withName(R.string.nav_sign_up).withIdentifier(4)
                        .withIcon(FontAwesome.Icon.faw_user_plus)
                        .withIconColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));

        mBrowseRecipesDrawerItem = new PrimaryDrawerItem().withName(R.string.drawer_browse_recipes).withIdentifier(6)
                .withIcon(FontAwesome.Icon.faw_list_alt)
                .withIconColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));


        mCreateRecipeDrawerItem = new PrimaryDrawerItem().withName(R.string.drawer_create_recipe).withIdentifier(7)
                .withIcon(FontAwesome.Icon.faw_plus_circle)
                .withIconColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));

        mSignOutDrawerItem =
                new PrimaryDrawerItem().withName(R.string.nav_sign_out).withIdentifier(5)
                        .withIcon(FontAwesome.Icon.faw_sign_out)
                        .withIconColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));

        mExerciseDrawerItem =
                new PrimaryDrawerItem().withName(R.string.nav_exercises).withIdentifier(9)
                        .withIcon(FontAwesome.Icon.faw_tencent_weibo)
                        .withIconColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));

        mChallangeDrawerItem =
                new PrimaryDrawerItem().withName(R.string.nav_challanges).withIdentifier(10)
                        .withIcon(FontAwesome.Icon.faw_puzzle_piece)
                        .withIconColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
    }

    public void addDrawer() {

        FirebaseUser user = mAuthProvider.getUser();

        setupDrawerHeader(user);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        long selectedDrawerItem = getIntent().getLongExtra("SELECTED_DRAWER_ITEM", 1);

        mDrawerBuilder = new DrawerBuilder()
                .withSelectedItem(selectedDrawerItem)
                .withActivity(this)
                .withToolbar(mToolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withAccountHeader(mDrawerHeader)
                .withRootView(R.id.drawer_layout);

        if (user == null) {
            mDrawer = mDrawerBuilder.addDrawerItems(
                    mHomeDrawerItem,
                    mSignInDrawerItem,
                    mSignUpDrawerItem)
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        Intent intent;

                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            switch ((int) drawerItem.getIdentifier()) {
                                case 1:
                                    mFragment = new HomeFragment();
                                    break;
                                case 3:
                                    intent = new Intent(getApplicationContext(), SignInActivity.class);
                                    break;
                                case 4:
                                    intent = new Intent(getApplicationContext(), SignUpActivity.class);
                                    break;
                            }

                            if (mFragment != null) {
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.content_container, mFragment)
                                        .commit();
                            }

                            if(intent != null){
                                intent.putExtra("SELECTED_DRAWER_ITEM", drawerItem.getIdentifier());
                                startActivity(intent);
                            }

                            return false;
                        }
                    })
                    .build();
        } else {
            mDrawer = mDrawerBuilder.addDrawerItems(
                    mHomeDrawerItem,
                    mProfileDrawerItem,
                    mSignOutDrawerItem,
                    mCreateRecipeDrawerItem,
                    mBrowseRecipesDrawerItem,
                    mChallangeDrawerItem,
                    mExerciseDrawerItem)
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

                        Intent intent;

                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                            switch ((int) drawerItem.getIdentifier()) {
                                case 1:
                                    mFragment = new HomeFragment();
                                    break;
                                case 2:
                                    intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                                    break;
                                case 6:
                                    intent = new Intent(getApplicationContext(), ListRecipesActivity.class);
                                    break;
                                case 7:
                                    intent = new Intent(getApplicationContext(), CreateRecipeActivity.class);
                                    break;
                                case 9:
                                    intent = new Intent(getApplicationContext(), ListExercisesActivity.class);
                                    break;
                                case 10:
                                    intent = new Intent(getApplicationContext(), ListChallangesActivity.class);
                                    break;
                                case 5:
                                    mAuthProvider.signOut();
                                    mFragment = new HomeFragment();
                                    updateDrawer();
                                    break;
                            }

                            if (mFragment != null) {
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.content_container, mFragment)
                                        .commit();
                            }

                            if(intent != null){
                                intent.putExtra("SELECTED_DRAWER_ITEM", drawerItem.getIdentifier());
                                startActivity(intent);
                            }

                            return false;
                        }
                    })
                    .build();
        }
    }

    public void updateDrawer() {
        mDrawer.removeHeader();
        addDrawer();
    }
}