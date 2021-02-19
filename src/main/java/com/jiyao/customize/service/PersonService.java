package com.jiyao.customize.service;

import com.jiyao.customize.common.Result;
import com.jiyao.customize.dto.hik.person.Card;
import com.jiyao.customize.dto.hik.person.CardS;
import com.jiyao.customize.dto.hik.person.Person;

import java.util.List;

/**
 * @author 36536
 */
public interface PersonService {

    /**
     * 添加人信息
     * @param person 人员信息
     * @return
     */
    Result addPerson(Person person);

    /**
     * 添加人脸信息
     * @param personId 人员ID
     * @param faceData 人脸信息
     * @return
     */
    Result addFace(String personId, String faceData);

    /**
     * 添加卡号
     * @param cardList 卡号信息
     * @return
     */
    Result addCard(CardS cardList);

    /**
     * 查询PersonID
     * @param certificateNo 参数名称：certificateNo，personId，phoneNo，jobNo
     * @param paramValue 参数值
     * @return
     */
    String queryPersonId(String certificateNo,List<String> paramValue);
}
