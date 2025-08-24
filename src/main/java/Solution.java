import datasource.MySessionFactory;
import entity.*;
import entity.enumirate.Rating;
import entity.enumirate.SpecialFeatures;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import service.*;

import java.util.List;
import java.util.Set;

public class Solution {
    private static final CustomerService customerService = MyServiceHelper.getInstance().getCustomerService();
    private static final AddressService addressService = MyServiceHelper.getInstance().getAddressService();
    private static final RentalService  rentalService = MyServiceHelper.getInstance().getRentalService();
    private static final StoreService storeService = MyServiceHelper.getInstance().getStoreService();
    private static final InventoryService inventoryService = MyServiceHelper.getInstance().getInventoryService();
    private static final FilmService filmService = MyServiceHelper.getInstance().getFilmService();
    private static final PaymentService paymentService = MyServiceHelper.getInstance().getPaymentService();
    private static final CategoryService categoryService = MyServiceHelper.getInstance().getCategoryService();
    private static final FilmTextService filmTextService = MyServiceHelper.getInstance().getFilmTextService();
    private static final ActorService actorService = MyServiceHelper.getInstance().getActorService();
    private static final LanguageService languageService = MyServiceHelper.getInstance().getLanguageService();


    public static void main(String[] args) {
        Address address = addressService.getAddressOrCreate("16 Rouse Street", "Pitee", "941021", "9182736452", "Berlin", "Germany");
        createCustomer("Johna", "Smitha", "Johna12.Smitha21@cool.com", address, 1L);
        returnRentalFilm(111L, 4L);
        rentalFilm(15L, 15L, 1L);

        Actor actor1 = actorService.getById(7L);
        Actor actor2 = actorService.getById(22L);
        Actor actor3 = actorService.getById(111L);
        Film film = createNewfilm("English", "Sci-Fi", "Split Fiction",
                "A unique action-adventure experience that keeps you on the edge og your couch with unexpected moments",
                2025, 300, 19.99, Rating.R, 7, 3.99,
                Set.of(actor1, actor2, actor3),
                SpecialFeatures.TRAILERS, SpecialFeatures.COMMENTARIES);
        System.out.println(film);
    }


    static Customer createCustomer(String firstName, String lastName, String email, Address address, Long storeId) {
        return customerService.createCustomer(firstName, lastName, email, address, storeId);
    }

    static int returnRentalFilm(Long customerId, Long filmId) {
        return rentalService.returnRentalFilm(customerId, filmId);
    }

    static void rentalFilm(Long customerId, Long filmId, Long storeId) {
        Store store = storeService.getStore(storeId);
        Customer customer = customerService.findCustomerById(customerId);
        List<Inventory> inventoryList = inventoryService.findInventoryByFilmId(filmId);
        List<Rental> rentalList = rentalService.getRentalByInventory(inventoryList);
        Rental rental;
        Film film = filmService.getFilm(filmId);
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            if (!rentalList.isEmpty()) {
                Inventory inventory = rentalList.get(0).getInventory();
                rental = rentalService.createRental(customer, inventory, store);
            }
            else {
                rental = rentalService.createRental(customer, inventoryService.createInventory(film, store), store);
            }
            paymentService.addPaymentByCustomerByRental(customer, store.getStaff(), rental, film.getRentalRate());
            session.getTransaction().commit();
        }
    }

    static Film createNewfilm(String nameLanguage, String nameCategory, String title, String description, Integer releaseYear, Integer length, Double replacementCost, Rating rating, Integer rentalDuration, Double rentalRate, Set<Actor> actorSet, SpecialFeatures... specialFeatures) {
        Language language;
        try {
            language = languageService.getLanguageByName(nameLanguage);
        }
        catch (NoResultException e) {
            Language newLanguage = new Language();
            newLanguage.setName(nameLanguage);
            try (Session session = MySessionFactory.getSessionFactory().openSession()) {
                session.beginTransaction();
                language = languageService.addLanguage(newLanguage);
                session.getTransaction().commit();
            }

        }
        Category category = categoryService.getCategoryByName(nameCategory);

        Film film = new Film();
        film.setActors(actorSet);
        film.setSpecialFeatures(specialFeatures);
        film.setTitle(title);
        film.setDescription(description);
        film.setReleaseYear(releaseYear);
        film.setLength(length);
        film.setReplacementCost(replacementCost);
        film.setRating(rating);
        film.setRentalDuration(rentalDuration);
        film.setRentalRate(rentalRate);
        film.setLanguage(language);
        film.setCategory(category);

        FilmText filmText = new FilmText();
        filmText.setTitle(title);
        filmText.setDescription(description);

        Film newFilm;
        try (Session session = MySessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            newFilm = filmService.addFilm(film);
            filmText.setFilmId(newFilm.getFilmId());
            filmTextService.addFilmText(filmText);
            session.getTransaction().commit();
        }
        return newFilm;
    }

}
