package com.zb.mapper;

import com.zb.pojo.Image;
import com.zb.vo.ImageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 图片的Mapper
 * @author 孙硕威
 */
@Mapper
public interface ImageMapper {


	/**
	 * 多条件查找图片
	 * @param imageVo
	 * @return
	 * @throws Exception
	 */
	public List<Image>	getImageListByMap(ImageVo imageVo)throws Exception;


	/**
	 * 添加图片
	 * @param image
	 * @return 受影响行数
	 * @throws Exception
	 */
	public Integer insertImage(Image image)throws Exception;

	/**
	 * 修改图片
	 * @param image
	 * @return
	 * @throws Exception
	 */
	public Integer updateImage(Image image)throws Exception;



}
