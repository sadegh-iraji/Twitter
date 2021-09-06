package ir.maktab.twitter.util;

import ir.maktab.twitter.domain.Comment;
import ir.maktab.twitter.domain.Like;
import ir.maktab.twitter.domain.Twitt;
import ir.maktab.twitter.domain.User;

import javax.persistence.RollbackException;
import java.util.List;
import java.util.stream.Collectors;

public class MenuOperations {

    public static void mainMenu() {

        boolean mainMenuRepeat = true;

        while (mainMenuRepeat) {
            ApplicationContext.getShowMenus().showMainMenu();
            int mainMenuSwitch = 0;
            mainMenuSwitch = ApplicationContext.intFromScannerReturn(mainMenuSwitch);

            switch (mainMenuSwitch) {
                case 1:
                    System.out.println("Signing Up :\n===============");
                    signUp();
                    break;
                case 2:
                    System.out.println("Signing in :\n===============");
                    User activeUser = signIn();
                    signedInNestedMenu(activeUser);
                    break;
                default:
                    mainMenuRepeat = false;
            }


        }
    }

    private static void signUp() {

        // ............ Get Email Address and Validation ..........//
        String email;
        do {
            System.out.println("Email Address:");
            email = ApplicationContext.getTextScanner().next();
        } while (!ApplicationContext.getUserService().isValidEmail(email));

        // ............ Get Username and Validation ............ //
        String username;
        do {
            System.out.println("Username:");
            username = ApplicationContext.getTextScanner().next();
        } while (!ApplicationContext.getUserService().isValidUsername(username));

        // ........... Get Password ..................... //
        String password;
        System.out.println("Password:");
        password = ApplicationContext.getTextScanner().next();

        // ........... Save User in Database .............. //
        ApplicationContext.getUserService().save(new User(email, username, password));
        System.out.println("*** Your Account Created Successfully ***");
    }

    private static User signIn() {

        String username;
        String password;

        while (true) {
            System.out.println("Username: ");
            username = ApplicationContext.getTextScanner().next();
            System.out.println("Password:");
            password = ApplicationContext.getTextScanner().next();
            if (ApplicationContext.getUserService().checkUsernamePassword(username, password)) {
                break;
            } else {
                System.out.println("----> Wrong Username or Password <----");
            }
        }

        // ............ Welcome Message ............ //
        System.out.println("Welcome " + "\uD83C\uDF3A " + username + " \uD83C\uDF3A");

        return ApplicationContext.getUserService().findByUsername(username);
    }

    private static void signedInNestedMenu(User activeUser) {

        boolean signedInMenuRepeat = true;
        while (signedInMenuRepeat) {
            ApplicationContext.getShowMenus().showSignedInMenu();
            int signInMenuSwitch = 0;
            signInMenuSwitch = ApplicationContext.intFromScannerReturn(signInMenuSwitch);

            switch (signInMenuSwitch) {
                case 1:
                    ApplicationContext.getShowDetails().showAccountInfo(activeUser);
                    editAccountInfo(activeUser);
                    break;
                case 2:
                    activeUser = writeNewTwitt(activeUser);
                    break;
                case 3:
                    ApplicationContext.getShowDetails().showUserTwitts(activeUser);
                    editOrDeleteTwitt(activeUser);
                    break;
                case 4:
                    List<Twitt> allTwitts = ApplicationContext.getShowDetails().showAllTwitts();
                    commentOrLikeTwitt(activeUser, allTwitts);
                    break;
                case 5:
                    searchUsername();
                    break;
                default:
                    signedInMenuRepeat = false;
            }
        }
    }

    private static void editAccountInfo(User activeUser) {
        boolean editAccountMenuRepeat = true;
        while (editAccountMenuRepeat) {
            ApplicationContext.getShowMenus().showEditAccountMenu();
            int editAccountSwitch = 0;
            editAccountSwitch = ApplicationContext.intFromScannerReturn(editAccountSwitch);

            switch (editAccountSwitch) {
                case 1:
                    activeUser = editEmailAddress(activeUser);
                    break;
                case 2:
                    activeUser = editPassword(activeUser);
                default:
                    editAccountMenuRepeat = false;
            }
        }
    }

    private static User editEmailAddress(User activeUser) {
        String newEmail;
        do {
            System.out.println("Enter New Email Address:");
            newEmail = ApplicationContext.getTextScanner().next();
        } while (!ApplicationContext.getUserService().isValidEmail(newEmail));

        activeUser = ApplicationContext.getUserService().save(activeUser);
        System.out.println("*** Your Email Changed Successfully ***");
        return activeUser;
    }

    private static User editPassword(User activeUser) {
        String password;
        System.out.println("Enter Your Present Password :");
        password = ApplicationContext.getTextScanner().next();
        if (activeUser.getPassword().equals(password)) {
            System.out.println("Enter Your New Password :");
            password = ApplicationContext.getTextScanner().next();
            activeUser.setPassword(password);
            activeUser = ApplicationContext.getUserService().save(activeUser);
            System.out.println("*** Your Password Changed Successfully ***");
        } else {
            System.out.println("----> Wrong Entered Password <----");
        }
        return activeUser;
    }

    private static User writeNewTwitt(User activeUser) {
        try {
            String twittContent;
            System.out.println("<=======================>\n" +
                    "Write Your Twitt Content (Max 280 words) : ");
            ApplicationContext.getTextScanner().nextLine();
            twittContent = ApplicationContext.getTextScanner().nextLine();
            activeUser.getTwits().add(new Twitt(twittContent, activeUser));
            activeUser =  ApplicationContext.getUserService().save(activeUser);
        } catch (RollbackException e){
            System.out.println("----> More than Max <----");
        }
        return activeUser;
    }

    private static void editOrDeleteTwitt(User activeUser) {
        boolean editOrDeleteRepeat = true;
        while (editOrDeleteRepeat) {
            ApplicationContext.getShowMenus().showEditOrDeleteTwittMenu();
            int editOrDeleteSwitch = 0;
            editOrDeleteSwitch = ApplicationContext.intFromScannerReturn(editOrDeleteSwitch);
            switch (editOrDeleteSwitch) {
                case 1:
                    editTwitt(activeUser);
                    break;
                case 2:
                    deleteTwitt(activeUser);
                    break;
                default:
                    editOrDeleteRepeat = false;
            }

        }
    }

    private static void editTwitt(User activeUser) {
        String newContent;
        long id = 0L;
        System.out.println("Enter Id of The Twitt You Wanna Edit : ");
        id = ApplicationContext.longFromScannerReturn(id);
        long finalId = id;

        try {
            List<Twitt> twitts = activeUser.getTwits().stream()
                    .filter(twitt -> twitt.getId() == finalId)
                    .collect(Collectors.toList());
            Twitt selectedTwitt = twitts.get(0);

            System.out.println("Enter Your New Content :");
            ApplicationContext.getTextScanner().nextLine();
            newContent = ApplicationContext.getTextScanner().nextLine();

            selectedTwitt.setContent(newContent);

            ApplicationContext.getTwittService().save(selectedTwitt);
        } catch (Exception e) {
            System.out.println("----> Wrong Id <----");
        }


    }

    private static void deleteTwitt(User activeUser) {
        long id = 0L;
        System.out.println("Enter Id of The Twitt You Wanna Remove : ");
        id = ApplicationContext.longFromScannerReturn(id);
        long finalId = id;

        try {
            List<Twitt> twitts = activeUser.getTwits().stream()
                    .filter(twitt -> twitt.getId() == finalId)
                    .collect(Collectors.toList());
            Twitt selectedTwitt = twitts.get(0);

            System.out.println("⚠ Are You Sure You Wanna Remove This Twitt ? (y/n)");
            String answer = ApplicationContext.getTextScanner().next();
            if (answer.equals("y")) {
                ApplicationContext.getTwittService().delete(selectedTwitt);
                System.out.println("*** Your Twitt Deleted Successfully ***");
            }
        } catch (Exception e) {
            System.out.println("----> Wrong Id <----");
        }
    }

    private static void commentOrLikeTwitt(User activeUser, List<Twitt> allTwitts) {
        boolean commentOrLikeRepeat = true;
        while (commentOrLikeRepeat) {
            ApplicationContext.getShowMenus().showCommentOrLikeMenu();
            int commentOrLikeSwitch = 0;
            commentOrLikeSwitch = ApplicationContext.intFromScannerReturn(commentOrLikeSwitch);

            switch (commentOrLikeSwitch) {
                case 1:
                    commentForTwitt(activeUser, allTwitts);
                    break;
                case 2:
                    editComments(activeUser, allTwitts);
                    break;
                case 3:
                    deleteComment(activeUser, allTwitts);
                    break;

                case 4:
                    likeTwitt(activeUser, allTwitts);
                    break;

                default:
                    commentOrLikeRepeat = false;
            }

        }
    }

    private static void commentForTwitt(User activeUser, List<Twitt> allTwitts) {
        String comment;
        long id = 0L;
        System.out.println("Enter Twitt Id You Wanna Write a Comment For :");
        id = ApplicationContext.longFromScannerReturn(id);
        long finalId = id;

        try {
            Twitt selectedTwitt = allTwitts.stream()
                    .filter(twitt -> twitt.getId() == finalId).collect(Collectors.toList()).get(0);

            System.out.println("Write Your Comment :");
            ApplicationContext.getTextScanner().nextLine();
            comment = ApplicationContext.getTextScanner().nextLine();

            Comment newComment = new Comment(comment, activeUser, selectedTwitt);
            selectedTwitt.getComments().add(newComment);
            ApplicationContext.getTwittService().save(selectedTwitt);
            System.out.println("*** Your Comment Added Successfully ***");

        } catch (Exception e) {
            System.out.println("----> Wrong Id <----");
        }

    }

    private static void editComments(User activeUser, List<Twitt> allTwitts) {
        long twittId = 0L;
        System.out.println("Enter Twitt Id You Wanna Edit Your Comment For :");
        twittId = ApplicationContext.longFromScannerReturn(twittId);
        long finalId = twittId;
        try {
            Twitt selectedTwitt = allTwitts.stream()
                    .filter(twitt -> twitt.getId() == finalId).collect(Collectors.toList()).get(0);

            List<Comment> selectedTwittComments = selectedTwitt.getComments();
            if (selectedTwittComments
                    .stream().anyMatch(comment -> comment.getUser().getId().equals(activeUser.getId()))) {

                List<Comment> activeUserComments = selectedTwittComments.stream()
                        .filter(comment -> comment.getUser().getId().equals(activeUser.getId()))
                        .collect(Collectors.toList());

                long commentId = 0L;
                ApplicationContext.getShowDetails().showTwittCommentOfUser(activeUser,selectedTwitt);
                System.out.println("Enter Id of Comment You Wanna Edit : ");
                commentId = ApplicationContext.longFromScannerReturn(commentId);
                long finalComId = commentId;
                Comment selectedComment = activeUserComments
                        .stream().filter(comment -> comment.getId().equals(finalComId))
                        .collect(Collectors.toList()).get(0);
                System.out.println("Enter Your New Content :");
                ApplicationContext.getTextScanner().nextLine();
                String newContent = ApplicationContext.getTextScanner().nextLine();
                selectedComment.setContent(newContent);
                ApplicationContext.getCommentService().save(selectedComment);
                System.out.println("*** Your Comment Edited Successfully ***");
            } else {
                System.out.println("You Didn't Leave a Comment For This Twitt");
            }
        } catch (Exception e) {
            System.out.println("----> Wrong Id <----");
        }
    }

    private static void deleteComment(User activeUser, List<Twitt> allTwitts) {
        long twittId = 0L;
        System.out.println("Enter Twitt Id You Wanna Delete Your Comment For :");
        twittId = ApplicationContext.longFromScannerReturn(twittId);
        long finalId = twittId;
        try {
            Twitt selectedTwitt = allTwitts.stream()
                    .filter(twitt -> twitt.getId() == finalId).collect(Collectors.toList()).get(0);

            List<Comment> selectedTwittComments = selectedTwitt.getComments();
            if (selectedTwittComments
                    .stream().anyMatch(comment -> comment.getUser().getId().equals(activeUser.getId()))) {

                List<Comment> activeUserComments = selectedTwittComments.stream()
                        .filter(comment -> comment.getUser().getId().equals(activeUser.getId()))
                        .collect(Collectors.toList());
                long commentId = 0L;
                ApplicationContext.getShowDetails().showTwittCommentOfUser(activeUser,selectedTwitt);
                System.out.println("Enter Id of Comment You Wanna Delete : ");
                commentId = ApplicationContext.longFromScannerReturn(commentId);
                long finalComId = commentId;
                Comment selectedComment = activeUserComments
                        .stream().filter(comment -> comment.getId().equals(finalComId))
                        .collect(Collectors.toList()).get(0);
                selectedTwitt.getComments().remove(selectedComment);
                ApplicationContext.getTwittService().save(selectedTwitt);
                System.out.println("*** Your Comment Deleted Successfully ***");
            } else {
                System.out.println("You Didn't Leave a Comment For This Twitt");
            }
        } catch (Exception e) {
            System.out.println("----> Wrong Id <----");
        }
    }

    private static void likeTwitt(User activeUser, List<Twitt> allTwitts) {
        long id = 0L;
        System.out.println("Enter Twitt Id You Wanna Like ❤ :");
        id = ApplicationContext.longFromScannerReturn(id);
        long finalId = id;
        try {
            Twitt selectedTwitt = allTwitts.stream()
                    .filter(twitt -> twitt.getId() == finalId).collect(Collectors.toList()).get(0);

            List<Like> selectedTwittLikes = selectedTwitt.getLikes();

            if (selectedTwittLikes.stream().noneMatch(like -> like.getUser().getId().equals(activeUser.getId()))) {
                selectedTwittLikes.add(new Like(activeUser, selectedTwitt));
                ApplicationContext.getTwittService().save(selectedTwitt);
            } else {
                System.out.println("----> You Already Liked This Twitt <----");
                System.out.println("~~~~~ Do You Want to DisLike 💔 ? (y/n) :");
                String answer = ApplicationContext.getTextScanner().next();
                if (answer.equals("y")) {
                    Like dislike = selectedTwittLikes.stream()
                            .filter(like -> like.getUser().getId().equals(activeUser.getId()))
                            .collect(Collectors.toList()).get(0);
                    selectedTwitt.getLikes().remove(dislike);
                    ApplicationContext.getLikeService().delete(dislike);
                    System.out.println("*** Successfully Disliked 💔 ***");
                }
            }
        } catch (Exception e) {
            System.out.println("----> Wrong Id <----");
        }
    }

    private static void searchUsername() {
        String username;
        System.out.println("Enter Username You Need to Search :");
        username = ApplicationContext.getTextScanner().next();
        try {
            ApplicationContext.getUserService().findByUsername(username);
            System.out.println("✅ User Found By This Username");
        } catch (Exception e) {
            System.out.println("❌ No User Found By This Username");
        }


    }

}
