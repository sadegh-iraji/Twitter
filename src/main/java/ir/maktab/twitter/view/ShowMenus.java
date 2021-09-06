package ir.maktab.twitter.view;

public class ShowMenus {

    public void showMainMenu() {
        System.out.println(
                "1. Sign Up\n" +
                        "2. Sign In\n" +
                        "3. Exit");
    }

    public void showSignedInMenu() {
        System.out.println(
                "1. Show and Edit Your Profile\n" +
                        "2. Write A New Twitt\n" +
                        "3. Show, Edit and Delete Your Twitts\n" +
                        "4. Show All Twitts\n" +
                        "5. Search a Username\n" +
                        "6. Sign Out"
        );
    }

    public void showEditAccountMenu(){
        System.out.println(
                "1. Edit Your Email\n" +
                        "2. Edit Your Password\n" +
                        "3. Back to Main Menu"
        );
    }

    public void showEditOrDeleteTwittMenu(){
        System.out.println(
                "1. Edit Your Twitts\n" +
                        "2. Delete Your Twitts\n" +
                        "3. Back to Main Menu"
        );
    }

    public void showCommentOrLikeMenu(){
        System.out.println(
                "1. Comment For a Twitt\n" +
                        "2. Edit Your Comments\n" +
                        "3. Remove a Comment\n" +
                        "4. Like a Twitt\n" +
                        "5. Back to Main Menu"
        );
    }
}
