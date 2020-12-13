/*
 * Filename: Command.java
 * Author: Scott
 * Date Created: 11/29/2020
 */

package edu.umgc.librarymanager.gui;

/**
 * This is a utility class that provides names for Action Commands. It is a final
 * class and each of the fields are constants that can be used for an ActionCommand.
 * @author Scott
 */
public final class Command {

    // COMMON

    /**
     * A string for an ActionCommand of 'login'.
     */
    public static final String LOGIN = "login";
    /**
     * A string for an ActionCommand of 'logout'.
     */
    public static final String LOGOUT = "logout";
    /**
     * A string for an ActionCommand of 'about'.
     */
    public static final String ABOUT = "about";
    /**
     * A string for an ActionCommand of 'help'.
     */
    public static final String HELP = "help";
    /**
     * A string for an ActionCommand of 'clear'.
     */
    public static final String CLEAR = "clear";

    // LIBRARIAN

    /**
     * A string for an ActionCommand of 'add_user'.
     */
    public static final String ADD_USER = "add_user";
    /**
     * A string for an ActionCommand of 'create_user'.
     */
    public static final String CREATE_USER = "create_user";
    /**
     * A string for an ActionCommand of 'update_user'.
     */
    public static final String UPDATE_USER = "update_user";
    /**
     * A string for an ActionCommand of 'manage_users'.
     */
    public static final String MANAGE_USERS = "manage_users";
    /**
     * A string for an ActionCommand of 'view_user'.
     */
    public static final String VIEW_USER = "view_user";
    /**
     * A string for an ActionCommand of 'delete_user'.
     */
    public static final String DELETE_USER = "delete_user";
    /**
     * A string for an ActionCommand of 'manage_update'.
     */
    public static final String MANAGE_UPDATE_USER = "manage_update";
    /**
     * A string for an ActionCommand of 'add_item'.
     */
    public static final String ADD_ITEM = "add_item";
    /**
     * A string for an ActionCommand of 'create_item'.
     */
    public static final String CREATE_ITEM = "create_item";
    /**
     * A string for an ActionCommand of 'remove_item'.
     */
    public static final String REMOVE_ITEM = "remove_item";
    /**
     * A string for an ActionCommand of 'view_item'.
     */
    public static final String VIEW_ITEM = "view_item";
    /**
     * A string for an ActionCommand of 'delete_item'.
     */
    public static final String DELETE_ITEM = "delete_item";
    /**
     * A string for an ActionCommand of 'checkout_item'.
     */
    public static final String CHECKOUT_ITEM = "checkout_item";
    /**
     * A string for an ActionCommand of 'return_item'.
     */
    public static final String RETURN_ITEM = "return_item";
    /**
     * A string for an ActionCommand of 'manage_items'.
     */
    public static final String MANAGE_ITEMS = "manage_items";
    /**
     * A string for an ActionCommand of 'manage_update_item'.
     */
    public static final String MANAGE_UPDATE_ITEM = "manage_update_item";
    /**
     * A string for an ActionCommand of 'item_returned'.
     */
    public static final String ITEM_RETURNED = "item_returned";
    /**
     * A string for an ActionCommand of 'librarian_menu'.
     */
    public static final String LIBRARIAN_MENU = "librarian_menu";
    /**
     * A string for an ActionCommand of 'librarian_menu'.
     */
    public static final String NOTIFY = "notify_user";

    // PATRON

    /**
     * A string for an ActionCommand of 'search'.
     */
    public static final String SEARCH = "search";
    /**
     * A string for an ActionCommand of 'search_press'.
     */
    public static final String SEARCH_PRESS = "search_press";
    /**
     * A string for an ActionCommand of 'advanced_search_press'.
     */
    public static final String ADV_SEARCH_PRESS = "advanced_search_press";
    /**
     * A string for an ActionCommand of 'advanced_search'.
     */
    public static final String ADVANCED_SEARCH = "advanced_search";
    /**
     * A string for an ActionCommand of 'advanced_search_clear'.
     */
    public static final String ADV_SEARCH_CLEAR = "advanced_search_clear";
    /**
     * A string for an ActionCommand of 'checked_items'.
     */
    public static final String CHECKED_ITEMS = "checked_items";
    /**
     * A string for an ActionCommand of 'profile'.
     */
    public static final String PROFILE = "profile";
    /**
     * A string for an ActionCommand of 'item_renewed'.
     */
    public static final String ITEM_RENEWED = "item_renewed";
    /**
     * A string for an ActionCommand of 'item_reserved'.
     */
    public static final String ITEM_RESERVED = "item_reserved";
    /**
     * A string for an ActionCommand of 'item_reserved'.
     */
    public static final String VIEW_FEES = "view_fees";
    /**
     * A string for an ActionCommand of 'patron_menu'.
     */
    public static final String PATRON_MENU = "patron_menu"; // TODO remove?


    private Command() {}

}
