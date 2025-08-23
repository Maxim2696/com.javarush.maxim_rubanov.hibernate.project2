package service;

import entity.Language;
import repository.entity_rep.LanguageRep;

public class LanguageService {
    private final LanguageRep languageRep;
    public LanguageService(LanguageRep languageRep) {
        this.languageRep = languageRep;
    }

    public Language getLanguageByName(String lang) {
        return languageRep.getLanguage(lang);
    }

    public Language addLanguage(Language language) {
        return languageRep.addLanguage(language);
    }
}
