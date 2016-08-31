package com.hpkj.core.util;

import java.util.List;

import com.hpkj.login.vo.FunPInfo;
import com.hpkj.login.vo.FunSInfo;
/**
 * 递归生成主菜单子项
 * @author guohe
 *
 */
public class MenuJsonCreat {
	public static String doJson(List<FunPInfo> pli,List<FunSInfo> sli){
		String funJson = null;

		if (pli.isEmpty()){
			return null;
		}else {
			String lstr = "{\"menus\":[";
			String mstr = "";
			
			for (int i=0;i<pli.size();i++){
			
				mstr += "{\"menuid\":\""+pli.get(i).getFunid()+"\",\"icon\":\"icon-sys\",\"menuname\":\""+pli.get(i).getFunname()+"\",";
				
				mstr += "\"menus\":[";
				for (int j=0;j<sli.size();j++){
					if (pli.get(i).getFunid().equals(sli.get(j).getFunpid())){
						
						mstr += "{\"menuid\":\""+sli.get(j).getFunid()+"\",\"menuname\":\""+sli.get(j).getFunname()+"\",\"icon\":\"icon-page\",\"url\":\""+sli.get(j).getFunurl()+"\"},";
						
					}

				}
				
				if (i==pli.size()-1){
					mstr += "]}";
				}else{
					mstr += "]},";
				}
				
				
				
			}
			
			String rstr ="]};";
			
			System.out.println(lstr+mstr+rstr);
			
			funJson = lstr+mstr+rstr;
//			{
//                "menus":[
//				           {"menuid":"1","icon":"icon-sys","menuname":"项目修改",
//					      "menus":[
//							{"menuid":"12","menuname":"OCR识别","icon":"icon-page","url":"goOcr"},
//							{"menuid":"13","menuname":"类","icon":"icon-class","url":"menu1/class.html"},
//							{"menuid":"14","menuname":"菜单","icon":"icon-role","url":"demo2.html"},
//							{"menuid":"15","menuname":"菜单","icon":"icon-set","url":"demo.html"},
//							{"menuid":"16","menuname":"菜单","icon":"icon-log","url":"demo1.html"}
//						    ]},
//				{"menuid":"8","icon":"icon-sys","menuname":"项目设计",
//					"menus":[{"menuid":"21","menuname":"项目分析","icon":"icon-nav","url":"menu2/tree2.html"},
//							{"menuid":"22","menuname":"菜单","icon":"icon-nav","url":"demo1.html"}
//						]
//				},{"menuid":"56","icon":"icon-sys","menuname":"菜单",
//					"menus":[{"menuid":"31","menuname":"菜单","icon":"icon-nav","url":"demo1.html"},
//							{"menuid":"32","menuname":"菜单","icon":"icon-nav","url":"demo2.html"}
//						]
//				},{"menuid":"28","icon":"icon-sys","menuname":"菜单",
//					"menus":[{"menuid":"41","menuname":"菜单","icon":"icon-nav","url":"demo.html"},
//							{"menuid":"42","menuname":"菜单","icon":"icon-nav","url":"demo1.html"},
//							{"menuid":"43","menuname":"菜单","icon":"icon-nav","url":"demo2.html"}
//						]
//				},{"menuid":"39","icon":"icon-sys","menuname":"菜单",
//					"menus":[{"menuid":"51","menuname":"菜单","icon":"icon-nav","url":"demo.html"},
//							{"menuid":"52","menuname":"菜单","icon":"icon-nav","url":"demo1.html"},
//							{"menuid":"53","menuname":"菜单","icon":"icon-nav","url":"demo2.html"}
//						]
//				}
//		]};
			
			
			return funJson;
		}
	}
}
