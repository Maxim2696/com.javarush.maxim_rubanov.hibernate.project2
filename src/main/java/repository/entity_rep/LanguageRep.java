package repository.entity_rep;

import entity.Language;

public interface LanguageRep {
    Language getLanguage(String lang);
    Language addLanguage(Language language);
}
