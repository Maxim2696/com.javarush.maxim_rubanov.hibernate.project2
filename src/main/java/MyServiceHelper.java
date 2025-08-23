import repository.*;
import service.*;

public class MyServiceHelper {
    private final CategoryService categoryService = new CategoryService(new CategoryRepImpl());
    private final FilmTextService filmTextService = new FilmTextService(new FilmTextRepImpl());
    private final ActorService actorService = new ActorService(new ActorRepImpl());
    private final LanguageService languageService = new LanguageService(new LanguageRepImpl());
    private final CountryService countryService = new CountryService(new CountryRepImpl());
    private final CityService cityService = new CityService(new CityRepImpl());
    private final CustomerService customerService = new CustomerService(new CustomerRepImpl());
    private final AddressService addressService = new AddressService(new AddressRepImpl(countryService, cityService));
    private final RentalService rentalService = new RentalService(new RentalRepImpl());
    private final StoreService storeService = new StoreService(new StoreRepImpl());
    private final InventoryService inventoryService = new InventoryService(new InventoryRepImpl());
    private final FilmService filmService = new FilmService(new FilmRepImpl());
    private final PaymentService paymentService = new PaymentService(new PaymentRepImpl());

    private static MyServiceHelper instance;

    public static MyServiceHelper getInstance() {
        if (instance == null) {
            return new MyServiceHelper();
        }
        return instance;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public FilmTextService getFilmTextService() {
        return filmTextService;
    }

    public ActorService getActorService() {
        return actorService;
    }

    public LanguageService getLanguageService() {
        return languageService;
    }

    public CountryService getCountryService() {
        return countryService;
    }

    public CityService getCityService() {
        return cityService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public AddressService getAddressService() {
        return addressService;
    }

    public RentalService getRentalService() {
        return rentalService;
    }

    public StoreService getStoreService() {
        return storeService;
    }

    public InventoryService getInventoryService() {
        return inventoryService;
    }

    public FilmService getFilmService() {
        return filmService;
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }
}
