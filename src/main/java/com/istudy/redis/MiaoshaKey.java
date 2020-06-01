package com.istudy.redis;

public class MiaoshaKey extends BasePrefix{


    private MiaoshaKey(String prefix) {
		super(prefix);
	}

	//库存 0：没有库存  1：还有库存
 	public static MiaoshaKey isStokNull = new MiaoshaKey("stocknull");

	public static MiaoshaKey getMiaoshaPath = new MiaoshaKey("miaoshapath");

}
