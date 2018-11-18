package org.javatribe.lottery.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.javatribe.lottery.po.WinPrize;
import org.javatribe.lottery.po.WinPrizeExample;

public interface WinPrizeMapper {
    int countByExample(WinPrizeExample example);

    int deleteByExample(WinPrizeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WinPrize record);

    int insertSelective(WinPrize record);

    List<WinPrize> selectByExample(WinPrizeExample example);

    WinPrize selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WinPrize record, @Param("example") WinPrizeExample example);

    int updateByExample(@Param("record") WinPrize record, @Param("example") WinPrizeExample example);

    int updateByPrimaryKeySelective(WinPrize record);

    int updateByPrimaryKey(WinPrize record);
}