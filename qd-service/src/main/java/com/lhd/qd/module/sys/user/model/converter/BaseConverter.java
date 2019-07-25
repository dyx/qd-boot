package com.lhd.qd.module.sys.user.model.converter;

import org.mapstruct.Context;
import org.mapstruct.Named;

import java.io.Serializable;
import java.util.Map;

/**
 * @author lhd
 */
public abstract class BaseConverter {

    @Named("trans")
    public Serializable trans(Serializable companyId, @Context Map<String, Map<Serializable, Serializable>> transMap) {

        Map<Serializable, Serializable> valueMap = transMap.get("");
        if (valueMap == null) {
            return null;
        }

        return valueMap.get(companyId);
    }

}
