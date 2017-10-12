package com.example.krasimir.fitness_friend.NavMain;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.krasimir.fitness_friend.CreateRecipe.CreateRecipeActivity;
import com.example.krasimir.fitness_friend.Home.HomeFragment;
import com.example.krasimir.fitness_friend.ListRecipes.ListRecipesActivity;
import com.example.krasimir.fitness_friend.R;
import com.example.krasimir.fitness_friend.SignIn.SignInActivity;
import com.example.krasimir.fitness_friend.SignUp.SignUpActivity;
import com.example.krasimir.fitness_friend.UserProfile.UserProfileActivity;
import com.example.krasimir.fitness_friend.base.authentication.AuthenticationProvider;
import com.google.firebase.auth.FirebaseUser;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class NavMainActivity extends DaggerAppCompatActivity {

    private Fragment mFragment;
    private Toolbar mToolbar;
    private Drawer mDrawer;
    private DrawerBuilder mDrawerBuilder;
    private AccountHeader mHeaderResult;
    private PrimaryDrawerItem mHomeDrawerItem;
    private PrimaryDrawerItem mSignInDrawerItem;
    private PrimaryDrawerItem mSignUpDrawerItem;
    private PrimaryDrawerItem mSignOutDrawerItem;
    private PrimaryDrawerItem mProfileDrawerItem;
    private PrimaryDrawerItem mCreateRecipeDrawerItem;
    private PrimaryDrawerItem mBrowseRecipesDrawerItem;

    @Inject
    AuthenticationProvider mAuthProvider;

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
        addDrawerItems();
        addDrawer();

        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    public void setupDrawerHeader(FirebaseUser user) {
        ProfileDrawerItem userProfile = new ProfileDrawerItem();

        if (user != null) {
            String userEmail = user.getEmail();
            String userName = user.getDisplayName();

                userProfile.withName(userEmail).withTextColor(Color.BLACK)
                        .withEmail(userName).withTextColor(Color.BLACK);


            userProfile.withName(userEmail).withTextColor(Color.BLACK)
                    .withEmail(userName).withTextColor(Color.BLACK);

        } else {
            userProfile
                    .withName("Annoymous").withTextColor(Color.BLACK)
                    .withEmail("Anonymous").withTextColor(Color.BLACK);
        }

        mHeaderResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.headerBackground)
                .addProfiles(userProfile)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
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
                .withAccountHeader(mHeaderResult)
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
                    mBrowseRecipesDrawerItem,
                    mCreateRecipeDrawerItem)
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