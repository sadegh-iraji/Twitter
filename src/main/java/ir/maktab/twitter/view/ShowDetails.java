package ir.maktab.twitter.view;

import ir.maktab.twitter.domain.Twitt;
import ir.maktab.twitter.domain.User;
import ir.maktab.twitter.util.ApplicationContext;

import java.util.List;

public class ShowDetails {

    public void showAccountInfo(User activeUser) {
        System.out.println(
                "<==============================>\n" +
                        "Your Username: " + activeUser.getUsername() + "\n" +
                        "Your Email Address: " + activeUser.getEmail() + "\n" +
                        "<==============================>"
        );
    }

    public void showUserTwitts(User activeUser) {
        List<Twitt> userTwitts = activeUser.getTwits();
        userTwitts.forEach(twitt -> System.out.println(
                "*********************\n" +
                        "Twitt Id : " + twitt.getId() + "\n" +
                        "Twitt Content: " + twitt.getContent() + "\n" +
                        "----------------------------------------------"
        ));
    }

    public List<Twitt> showAllTwitts() {
        List<Twitt> twitts = ApplicationContext.getTwittService().findAll();

        twitts.forEach(twitt -> {
            System.out.println(
                    "*********************\n" +
                            "Twitt Id :" + twitt.getId() + "\n" +
                            twitt.getUser().getUsername() + " Twitts :\n" +
                            twitt.getContent() + "\n" +
                            "====>Comments:\n"
            );
            if (twitt.getComments().isEmpty()) {
                System.out.println("----> No Comments For This Twitt Yet <----");
            } else {
                twitt.getComments().forEach(comment -> System.out.println(
                        "@" + comment.getUser().getUsername() + " : " + comment.getContent() + "\n"
                ));
            }
            System.out.println("====>Likes : " + twitt.getLikes().size() + " ❤");
            System.out.println("*********************");
        });

        return twitts;
    }

    public void showTwittComments(Twitt twitt) {
        twitt.getComments().forEach(comment -> System.out.println(
                "<====================\n" +
                        "Comment Id : " + comment.getId() + "\n" +
                        "Comment : " + comment.getContent() + "\n" +
                        "==============>"
        ));
    }

    public void showTwittCommentOfUser(User activeUser, Twitt twitt) {
        twitt.getComments().stream().filter(comment -> comment.getUser().getId().equals(activeUser.getId()))
                .forEach(comment -> System.out.println(
                        "<====================\n" +
                                "Comment Id : " + comment.getId() + "\n" +
                                "Comment : " + comment.getContent() + "\n" +
                                "==============>"));
    }
}
