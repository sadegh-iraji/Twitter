package ir.maktab.twitter.util;

import ir.maktab.twitter.repository.CommentRepository;
import ir.maktab.twitter.repository.LikeRepository;
import ir.maktab.twitter.repository.TwittRepository;
import ir.maktab.twitter.repository.UserRepository;
import ir.maktab.twitter.repository.impl.CommentRepositoryImpl;
import ir.maktab.twitter.repository.impl.LikeRepositoryImpl;
import ir.maktab.twitter.repository.impl.TwittRepositoryImpl;
import ir.maktab.twitter.repository.impl.UserRepositoryImpl;
import ir.maktab.twitter.service.CommentService;
import ir.maktab.twitter.service.LikeService;
import ir.maktab.twitter.service.TwittService;
import ir.maktab.twitter.service.UserService;
import ir.maktab.twitter.service.impl.CommentServiceImpl;
import ir.maktab.twitter.service.impl.LikeServiceImpl;
import ir.maktab.twitter.service.impl.TwittServiceImpl;
import ir.maktab.twitter.service.impl.UserServiceImpl;
import ir.maktab.twitter.view.ShowDetails;
import ir.maktab.twitter.view.ShowMenus;

import javax.persistence.EntityManager;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ApplicationContext {

    private ApplicationContext() {
    }

    private static final UserRepository userRepository;

    private static final UserService userService;

    static {
        EntityManager userManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        userRepository = new UserRepositoryImpl(userManager);
        userService = new UserServiceImpl(userRepository);
    }

    private static final TwittRepository twittRepository;

    private static final TwittService twittService;

    static {
        EntityManager twittManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        twittRepository = new TwittRepositoryImpl(twittManager);
        twittService = new TwittServiceImpl(twittRepository);
    }

    private static final CommentRepository commentRepository;

    private static final CommentService commentService;

    static {
        EntityManager commentManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        commentRepository = new CommentRepositoryImpl(commentManager);
        commentService = new CommentServiceImpl(commentRepository);
    }

    private static final LikeRepository likeRepository;

    private static final LikeService likeService;

    static {
        EntityManager likeManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        likeRepository = new LikeRepositoryImpl(likeManager);
        likeService = new LikeServiceImpl(likeRepository);
    }

    private static final ShowMenus showMenus = new ShowMenus();

    private static final Scanner textScanner = new Scanner(System.in);

    private static final Scanner numericScanner = new Scanner(System.in);

    private static final ShowDetails showDetails = new ShowDetails();

    public static ShowDetails getShowDetails() {
        return showDetails;
    }

    public static int intFromScannerReturn(int number) {
        do {
            try {
                number = getNumericScanner().nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please Enter an Integer Number !");
                getNumericScanner().next();
            }
        } while (number < 1);
        return number;
    }

    public static long longFromScannerReturn(long number) {
        do {
            try {
                number = ApplicationContext.getNumericScanner().nextLong();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Please Enter an Long Number !");
                ApplicationContext.getNumericScanner().next();
            }
        } while (number < 1);
        return number;
    }

    public static ShowMenus getShowMenus() {
        return showMenus;
    }

    public static Scanner getTextScanner() {
        return textScanner;
    }

    public static Scanner getNumericScanner() {
        return numericScanner;
    }

    public static UserService getUserService() {
        return userService;
    }

    public static TwittService getTwittService() {
        return twittService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }

    public static LikeService getLikeService() {
        return likeService;
    }
}
