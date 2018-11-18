package org.javatribe.lottery.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.javatribe.lottery.po.Prize;
import org.javatribe.lottery.po.PrizeExample;

public interface PrizeMapper {
    int countByExample(PrizeExample example);

    int deleteByExample(PrizeExample example);

    int insert(Prize record);

    int insertSelective(Prize record);

    List<Prize> selectByExample(PrizeExample example);

    int updateByExampleSelective(@Param("record") Prize record, @Param("example") PrizeExample example);

    int updateByExample(@Param("record") Prize record, @Param("example") PrizeExample example);
}