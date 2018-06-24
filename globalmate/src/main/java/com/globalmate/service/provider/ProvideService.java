package com.globalmate.service.provider;

import com.globalmate.data.dao.mapper.TProvideMapper;
import com.globalmate.data.entity.TProvide;
import com.globalmate.data.entity.User;
import com.globalmate.exception.DataNotFoundException;
import com.globalmate.uitl.IdGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class ProvideService implements IProvideService {

    @Autowired
    private TProvideMapper provideMapper;

    @Override
    public TProvide addProvide(User user, TProvide provide) {
        checkNotNull(user);
        checkNotNull(user.getId());
        checkNotNull(provide);

        if (StringUtils.isBlank(provide.getId())) {
            provide.setId(IdGenerator.generateId());
        }
        if (StringUtils.isBlank(provide.getuId())) {
            provide.setuId(user.getId());
        }
        if (StringUtils.isBlank(provide.getuName())) {
            provide.setuName(user.getNikename() != null ? user.getNikename() : user.getName());
        }
        provide.setpCreateTime(Date.from(Instant.now()));

        int insert = provideMapper.insert(provide);
        if (insert > 1) {
            return provideMapper.selectByPrimaryKey(provide.getId());
        }

        return null;
    }

    @Override
    public TProvide updateProvide(User user, TProvide provide) {
        checkNotNull(provide);
        checkNotNull(provide.getId());

        TProvide tProvide = provideMapper.selectByPrimaryKey(provide.getId());
        if (tProvide == null) {
            throw new DataNotFoundException("没找到id为[" + provide.getId() +
                    "]的数据！");
        }

        int i = provideMapper.updateByPrimaryKeySelective(provide);
        if (i > 0) {
            return provideMapper.selectByPrimaryKey(provide.getId());
        }

        return null;
    }

    @Override
    public List<TProvide> getProvide(User user) {

        TProvide provide = new TProvide();
        if (user != null && user.getId() != null) {
            provide.setuId(user.getId());
        }

        List<TProvide> tProvides = provideMapper.selectProvides(provide);

        return tProvides;
    }
}
