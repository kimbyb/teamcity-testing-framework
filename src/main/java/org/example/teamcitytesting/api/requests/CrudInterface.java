package org.example.teamcitytesting.api.requests;

import org.example.teamcitytesting.api.models.BaseModel;

public interface CrudInterface  {
    Object create(BaseModel model);
    Object read(String id);
    Object update(String id, BaseModel model);
    Object delete(String id);
}
