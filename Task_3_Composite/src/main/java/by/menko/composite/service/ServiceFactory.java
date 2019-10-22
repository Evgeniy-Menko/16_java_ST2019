package by.menko.composite.service;

public final class ServiceFactory {
    /**
     * Object class.
     */
    private static ServiceFactory serviceFactory;
    /**
     * Object chooseLanguage.
     */
    private ChooseLanguage chooseLanguage;
    /**
     * Collect text.
     */
    private CollectText collectText;
    /**
     * Object ReadFileAndAddStorage.
     */
    private ReadFileAndAddStorage readFileAndAddStorage;
    /**
     * Object SortByCount.
     */
    private SortByCount sortByCount;
    /**
     * ObjectSortByCount.
     */
    private SortBySymbolService sortBySymbolService;

    /**
     * Private constructor.
     */
    private ServiceFactory() {
        chooseLanguage = new ChooseLanguage();
        collectText = new CollectText();
        readFileAndAddStorage = new ReadFileAndAddStorage();
        sortBySymbolService = new SortBySymbolService();
        sortByCount = new SortByCount();
    }

    /**
     * Singleton.
     *
     * @return object matrix.
     */
    public static synchronized ServiceFactory getInstance() {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }

    /**
     * Getter for ChooseLanguage.
     *
     * @return chooseLanguage.
     */
    public ChooseLanguage getChooseLanguage() {
        return chooseLanguage;
    }

    /**
     * Getter for CollectText.
     *
     * @return object collectText.
     */
    public CollectText getCollectText() {
        return collectText;
    }

    /**
     * Getter for readFileAndAddStorage.
     *
     * @return object readFileAndAddStorage.
     */
    public ReadFileAndAddStorage getReadFileAndAddStorage() {
        return readFileAndAddStorage;
    }

    /**
     * Getter for sortByCount.
     *
     * @return object sortByCount.
     */
    public SortByCount getSortByCount() {
        return sortByCount;
    }

    /**
     * Getter for sortBySymbolService.
     *
     * @return object sortBySymbolService.
     */
    public SortBySymbolService getSortBySymbolService() {
        return sortBySymbolService;
    }
}
