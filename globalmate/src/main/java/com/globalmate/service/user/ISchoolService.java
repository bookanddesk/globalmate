package com.globalmate.service.user;

import com.globalmate.data.entity.SysSchool;

import java.util.List;

/**
 * @author XingJiajun
 * @Date 2018/8/17 16:29
 * @Description
 */
public interface ISchoolService {

    int addSchool(SysSchool sysSchool);

    List<SysSchool> getSchools(SysSchool school);

}
