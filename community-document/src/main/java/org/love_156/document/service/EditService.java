package org.love_156.document.service;

import org.community.common.Result;
import org.love_156.document.entity.Document;

public interface EditService {
    Result<?> CreateDocument(Document document);
    Result<?> Edit();
    Result<?> Visit();
    Result<?> EditUpdate();
    Result<?> EditAbandon();
}
