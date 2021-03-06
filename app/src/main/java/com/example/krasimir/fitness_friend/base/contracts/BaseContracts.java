package com.example.krasimir.fitness_friend.base.contracts;

/**
 * Created by Krasimir on 10/10/2017.
 */

public abstract class BaseContracts {
    /**
     * Base view for MVP
     *
     * @param <T> {@link BaseContracts.Presenter} class
     */
    public interface View<T extends Presenter> {
        /**
         * Sets the presenter
         *
         * @param presenter {@link Presenter} object
         */
        void setPresenter(T presenter);
    }

    /**
     * Base presenter for MVP
     *
     * @param <T> {@link BaseContracts.View} class
     */
    public interface Presenter<T extends View> {

        /**
         * Attaches the view to the presenter and the presenters starts preparing data
         * @param view the {@link View} of the presenter
         */
        void subscribe(T view);


        /**
         * Releases the presenter
         */
        void unsubscribe();
    }
}
