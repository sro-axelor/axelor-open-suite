/*
 * Axelor Business Solutions
 *
 * Copyright (C) 2023 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.axelor.apps.base.service;

import com.axelor.apps.base.db.Partner;
import com.axelor.apps.base.db.Product;
import com.axelor.apps.tool.service.TranslationService;
import com.google.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class InternationalServiceImpl implements InternationalService {

  protected TranslationService translationService;

  @Inject
  public InternationalServiceImpl(TranslationService translationService) {
    this.translationService = translationService;
  }

  @Override
  public String translate(String source, String sourceLanguage, String targetLanguage) {
    String translationKey = translationService.getTranslationKey(source, sourceLanguage);
    return translationService.getValueTranslation(translationKey, targetLanguage);
  }

  @Override
  public Map<String, String> getProductDescriptionAndNameTranslation(
      Product product, Partner partner, String userLanguage) {
    Map<String, String> translation = new HashMap<>();
    if (product != null && partner != null) {
      String partnerLanguage = partner.getLanguage().getCode();
      translation.put(
          "description", translate(product.getDescription(), userLanguage, partnerLanguage));
      translation.put("productName", translate(product.getName(), userLanguage, partnerLanguage));
    }
    return translation;
  }
}
