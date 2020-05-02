package com.yicheng.tourism.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 用于生成测试数据的公共方法
 */
public class CreateTestDataUtil {

    private static String[] road="重庆大厦,黑龙江路,十梅庵街,遵义路,湘潭街,瑞金广场,仙山街,仙山东路,仙山西大厦,白沙河路,赵红广场,机场路,民航街,长城南路,流亭立交桥,虹桥广场,长城大厦,礼阳路,风岗街,中川路,白塔广场,兴阳路,文阳街,绣城路,河城大厦,锦城广场,崇阳街,华城路,康城街,正阳路,和阳广场,中城路,江城大厦,顺城路,安城街,山城广场,春城街,国城路,泰城街,德阳路,明阳大厦,春阳路,艳阳街,秋阳路,硕阳街,青威高速,瑞阳街,丰海路,双元大厦,惜福镇街道,夏庄街道,古庙工业园,中山街,太平路,广西街,潍县广场,博山大厦,湖南路,济宁街,芝罘路,易州广场,荷泽四路,荷泽二街,荷泽一路,荷泽三大厦,观海二广场,广西支街,观海一路,济宁支街,莒县路,平度广场,明水路,蒙阴大厦,青岛路,湖北街,江宁广场,郯城街,天津路,保定街,安徽路,河北大厦,黄岛路,北京街,莘县路,济南街,宁阳广场,日照街,德县路,新泰大厦,荷泽路,山西广场,沂水路,肥城街,兰山路,四方街,平原广场,泗水大厦,浙江路,曲阜街,寿康路,河南广场,泰安路,大沽街,红山峡支路,西陵峡一大厦,台西纬一广场,台西纬四街,台西纬二路,西陵峡二街,西陵峡三路,台西纬三广场,台西纬五路,明月峡大厦,青铜峡路,台西二街,观音峡广场,瞿塘峡街,团岛二路,团岛一街,台西三路,台西一大厦,郓城南路,团岛三街,刘家峡路,西藏二街,西藏一广场,台西四街,三门峡路,城武支大厦,红山峡路,郓城北广场,龙羊峡路,西陵峡街,台西五路,团岛四街,石村广场,巫峡大厦,四川路,寿张街,嘉祥路,南村广场,范县路,西康街,云南路,巨野大厦,西江广场,鱼台街,单县路,定陶街,滕县路,钜野广场,观城路,汶上大厦,朝城路,滋阳街,邹县广场,濮县街,磁山路,汶水街,西藏路,城武大厦,团岛路,南阳街,广州路,东平街,枣庄广场,贵州街,费县路,南海大厦,登州路,文登广场,信号山支路,延安一街,信号山路,兴安支街,福山支广场,红岛支大厦,莱芜二路,吴县一街,金口三路,金口一广场,伏龙山路,鱼山支街,观象二路,吴县二大厦,莱芜一广场,金口二街,海阳路,龙口街,恒山路,鱼山广场,掖县路,福山大厦,红岛路,常州街,大学广场,龙华街,齐河路,莱阳街,黄县路,张店大厦,祚山路,苏州街,华山路,伏龙街,江苏广场,龙江街,王村路,琴屿大厦,齐东路,京山广场,龙山路,牟平街,延安三路,延吉街,南京广场,东海东大厦,银川西路,海口街,山东路,绍兴广场,芝泉路,东海中街,宁夏路,香港西大厦,隆德广场,扬州街,郧阳路,太平角一街,宁国二支路,太平角二广场,天台东一路,太平角三大厦,漳州路一路,漳州街二街,宁国一支广场,太平角六街,太平角四路,天台东二街,太平角五路,宁国三大厦,澳门三路,江西支街,澳门二路,宁国四街,大尧一广场,咸阳支街,洪泽湖路,吴兴二大厦,澄海三路,天台一广场,新湛二路,三明北街,新湛支路,湛山五街,泰州三广场,湛山四大厦,闽江三路,澳门四街,南海支路,吴兴三广场,三明南路,湛山二街,二轻新村镇,江南大厦,吴兴一广场,珠海二街,嘉峪关路,高邮湖街,湛山三路,澳门六广场,泰州二路,东海一大厦,天台二路,微山湖街,洞庭湖广场,珠海支街,福州南路,澄海二街,泰州四路,香港中大厦,澳门五路,新湛三街,澳门一路,正阳关街,宁武关广场,闽江四街,新湛一路,宁国一大厦,王家麦岛,澳门七广场,泰州一路,泰州六街,大尧二路,青大一街,闽江二广场,闽江一大厦,屏东支路,湛山一街,东海西路,徐家麦岛函谷关广场,大尧三路,晓望支街,秀湛二路,逍遥三大厦,澳门九广场,泰州五街,澄海一路,澳门八街,福州北路,珠海一广场,宁国二路,临淮关大厦,燕儿岛路,紫荆关街,武胜关广场,逍遥一街,秀湛四路,居庸关街,山海关路,鄱阳湖大厦,新湛路,漳州街,仙游路,花莲街,乐清广场,巢湖街,台南路,吴兴大厦,新田路,福清广场,澄海路,莆田街,海游路,镇江街,石岛广场,宜兴大厦,三明路,仰口街,沛县路,漳浦广场,大麦岛,台湾街,天台路,金湖大厦,高雄广场,海江街,岳阳路,善化街,荣成路,澳门广场,武昌路,闽江大厦,台北路,龙岩街,咸阳广场,宁德街,龙泉路,丽水街,海川路,彰化大厦,金田路,泰州街,太湖路,江西街,泰兴广场,青大街,金门路,南通大厦,旌德路,汇泉广场,宁国路,泉州街,如东路,奉化街,鹊山广场,莲岛大厦,华严路,嘉义街,古田路,南平广场,秀湛路,长汀街,湛山路,徐州大厦,丰县广场,汕头街,新竹路,黄海街,安庆路,基隆广场,韶关路,云霄大厦,新安路,仙居街,屏东广场,晓望街,海门路,珠海街,上杭路,永嘉大厦,漳平路,盐城街,新浦路,新昌街,高田广场,市场三街,金乡东路,市场二大厦,上海支路,李村支广场,惠民南路,市场纬街,长安南路,陵县支街,冠县支广场,小港一大厦,市场一路,小港二街,清平路,广东广场,新疆路,博平街,港通路,小港沿,福建广场,高唐街,茌平路,港青街,高密路,阳谷广场,平阴路,夏津大厦,邱县路,渤海街,恩县广场,旅顺街,堂邑路,李村街,即墨路,港华大厦,港环路,馆陶街,普集路,朝阳街,甘肃广场,港夏街,港联路,陵县大厦,上海路,宝山广场,武定路,长清街,长安路,惠民街,武城广场,聊城大厦,海泊路,沧口街,宁波路,胶州广场,莱州路,招远街,冠县路,六码头,金乡广场,禹城街,临清路,东阿街,吴淞路,大港沿,辽宁路,棣纬二大厦,大港纬一路,贮水山支街,无棣纬一广场,大港纬三街,大港纬五路,大港纬四街,大港纬二路,无棣二大厦,吉林支路,大港四街,普集支路,无棣三街,黄台支广场,大港三街,无棣一路,贮水山大厦,泰山支路,大港一广场,无棣四路,大连支街,大港二路,锦州支街,德平广场,高苑大厦,长山路,乐陵街,临邑路,嫩江广场,合江路,大连街,博兴路,蒲台大厦,黄台广场,城阳街,临淄路,安邱街,临朐路,青城广场,商河路,热河大厦,济阳路,承德街,淄川广场,辽北街,阳信路,益都街,松江路,流亭大厦,吉林路,恒台街,包头路,无棣街,铁山广场,锦州街,桓台路,兴安大厦,邹平路,胶东广场,章丘路,丹东街,华阳路,青海街,泰山广场,周村大厦,四平路,台东西七街,台东东二路,台东东七广场,台东西二路,东五街,云门二路,芙蓉山村,延安二广场,云门一街,台东四路,台东一街,台东二路,杭州支广场,内蒙古路,台东七大厦,台东六路,广饶支街,台东八广场,台东三街,四平支路,郭口东街,青海支路,沈阳支大厦,菜市二路,菜市一街,北仲三路,瑞云街,滨县广场,庆祥街,万寿路,大成大厦,芙蓉路,历城广场,大名路,昌平街,平定路,长兴街,浦口广场,诸城大厦,和兴路,德盛街,宁海路,威海广场,东山路,清和街,姜沟路,雒口大厦,松山广场,长春街,昆明路,顺兴街,利津路,阳明广场,人和路,郭口大厦,营口路,昌邑街,孟庄广场,丰盛街,埕口路,丹阳街,汉口路,洮南大厦,桑梓路,沾化街,山口路,沈阳街,南口广场,振兴街,通化路,福寺大厦,峄县路,寿光广场,曹县路,昌乐街,道口路,南九水街,台湛广场,东光大厦,驼峰路,太平山,标山路,云溪广场,太清路".split(",");
    private static String surName[] = {"赵","钱","孙","李","周","吴","郑","王","冯","陈","楮","卫","蒋","沈","韩","杨","朱","秦","尤","许","何","吕","施","张","孔","曹","严","华","金","魏","陶","姜", "戚","谢","邹","喻","柏","水","窦","章","云","苏","潘","葛","奚","范","彭","郎", "鲁","韦","昌","马","苗","凤","花","方","俞","任","袁","柳","酆","鲍","史","唐", "费","廉","岑","薛","雷","贺","倪","汤","滕","殷","罗","毕","郝","邬","安","常", "乐","于","时","傅","皮","卞","齐","康","伍","余","元","卜","顾","孟","平","黄", "和","穆","萧","尹","姚","邵","湛","汪","祁","毛","禹","狄","米","贝","明","臧", "计","伏","成","戴","谈","宋","茅","庞","熊","纪","舒","屈","项","祝","董","梁", "杜","阮","蓝","闽","席","季","麻","强","贾","路","娄","危","江","童","颜","郭", "梅","盛","林","刁","锺","徐","丘","骆","高","夏","蔡","田","樊","胡","凌","霍", "虞","万","支","柯","昝","管","卢","莫","经","房","裘","缪","干","解","应","宗", "丁","宣","贲","邓","郁","单","杭","洪","包","诸","左","石","崔","吉","钮","龚", "程","嵇","邢","滑","裴","陆","荣","翁","荀","羊","於","惠","甄","麹","家","封", "芮","羿","储","靳","汲","邴","糜","松","井","段","富","巫","乌","焦","巴","弓", "牧","隗","山","谷","车","侯","宓","蓬","全","郗","班","仰","秋","仲","伊","宫", "宁","仇","栾","暴","甘","斜","厉","戎","祖","武","符","刘","景","詹","束","龙", "叶","幸","司","韶","郜","黎","蓟","薄","印","宿","白","怀","蒲","邰","从","鄂", "索","咸","籍","赖","卓","蔺","屠","蒙","池","乔","阴","郁","胥","能","苍","双", "闻","莘","党","翟","谭","贡","劳","逄","姬","申","扶","堵","冉","宰","郦","雍", "郤","璩","桑","桂","濮","牛","寿","通","边","扈","燕","冀","郏","浦","尚","农", "温","别","庄","晏","柴","瞿","阎","充","慕","连","茹","习","宦","艾","鱼","容", "向","古","易","慎","戈","廖","庾","终","暨","居","衡","步","都","耿","满","弘", "匡","国","文","寇","广","禄","阙","东","欧","殳","沃","利","蔚","越","夔","隆", "师","巩","厍","聂","晁","勾","敖","融","冷","訾","辛","阚","那","简","饶","空", "曾","毋","沙","乜","养","鞠","须","丰","巢","关","蒯","相","查","后","荆","红", "游","竺","权","逑","盖","益","桓","公","晋","楚","阎","法","汝","鄢","涂","钦", "岳","帅","缑","亢","况","后","有","琴","商","牟","佘","佴","伯","赏","墨","哈", "谯","笪","年","爱","阳","佟"};
    private static String doubleSurName[] = {"万俟","司马","上官","欧阳","夏侯","诸葛","闻人","东方", "赫连","皇甫","尉迟","公羊","澹台","公冶","宗政","濮阳","淳于","单于","太叔","申屠", "公孙","仲孙","轩辕","令狐","锺离","宇文","长孙","慕容","鲜于","闾丘","司徒","司空", "丌官","司寇","仉","督","子车","颛孙","端木","巫马","公西","漆雕","乐正","壤驷","公良", "拓拔","夹谷","宰父","谷梁","段干","百里","东郭","南门","呼延","归","海","羊舌","微生", "梁丘","左丘","东门","西门","南宫"};
    private static String[] word = {"一","乙","二","十","丁","厂","七","卜","人","入","八","九","几","儿","了","力","乃","刀","又", "三","于","干","亏","士","工","土","才","寸","下","大","丈","与","万","上","小","口","巾","山", "千","乞","川","亿","个","勺","久","凡","及","夕","丸","么","广","亡","门","义","之","尸","弓", "己","已","子","卫","也","女","飞","刃","习","叉","马","乡","丰","王","井","开","夫","天","无", "元","专","云","扎","艺","木","五","支","厅","不","太","犬","区","历","尤","友","匹","车","巨", "牙","屯","比","互","切","瓦","止","少","日","中","冈","贝","内","水","见","午","牛","手","毛", "气","升","长","仁","什","片","仆","化","仇","币","仍","仅","斤","爪","反","介","父","从","今", "凶","分","乏","公","仓","月","氏","勿","欠","风","丹","匀","乌","凤","勾","文","六","方","火", "为","斗","忆","订","计","户","认","心","尺","引","丑","巴","孔","队","办","以","允","予","劝", "双","书","幻","玉","刊","示","末","未","击","打","巧","正","扑","扒","功","扔","去","甘","世", "古","节","本","术","可","丙","左","厉","右","石","布","龙","平","灭","轧","东","卡","北","占", "业","旧","帅","归","且","旦","目","叶","甲","申","叮","电","号","田","由","史","只","央","兄", "叼","叫","另","叨","叹","四","生","失","禾","丘","付","仗","代","仙","们","仪","白","仔","他", "斥","瓜","乎","丛","令","用","甩","印","乐","句","匆","册","犯","外","处","冬","鸟","务","包", "饥","主","市","立","闪","兰","半","汁","汇","头","汉","宁","穴","它","讨","写","让","礼","训", "必","议","讯","记","永","司","尼","民","出","辽","奶","奴","加","召","皮","边","发","孕","圣", "对","台","矛","纠","母","幼","丝","式","刑","动","扛","寺","吉","扣","考","托","老","执","巩", "圾","扩","扫","地","扬","场","耳","共","芒","亚","芝","朽","朴","机","权","过","臣","再","协", "西","压","厌","在","有","百","存","而","页","匠","夸","夺","灰","达","列","死","成","夹","轨", "邪","划","迈","毕","至","此","贞","师","尘","尖","劣","光","当","早","吐","吓","虫","曲","团", "同","吊","吃","因","吸","吗","屿","帆","岁","回","岂","刚","则","肉","网","年","朱","先","丢", "舌","竹","迁","乔","伟","传","乒","乓","休","伍","伏","优","伐","延","件","任","伤","价","份", "华","仰","仿","伙","伪","自","血","向","似","后","行","舟","全","会","杀","合","兆","企","众", "爷","伞","创","肌","朵","杂","危","旬","旨","负","各","名","多","争","色","壮","冲","冰","庄", "庆","亦","刘","齐","交","次","衣","产","决","充","妄","闭","问","闯","羊","并","关","米","灯", "州","汗","污","江","池","汤","忙","兴","宇","守","宅","字","安","讲","军","许","论","农","讽", "设","访","寻","那","迅","尽","导","异","孙","阵","阳","收","阶","阴","防","奸","如","妇","好", "她","妈","戏","羽","观","欢","买","红","纤","级","约","纪","驰","巡","寿","弄","麦","形","进", "戒","吞","远","违","运","扶","抚","坛","技","坏","扰","拒","找","批","扯","址","走","抄","坝", "贡","攻","赤","折","抓","扮","抢","孝","均","抛","投","坟","抗","坑","坊","抖","护","壳","志", "扭","块","声","把","报","却","劫","芽","花","芹","芬","苍","芳","严","芦","劳","克","苏","杆", "杠","杜","材","村","杏","极","李","杨","求","更","束","豆","两","丽","医","辰","励","否","还", "歼","来","连","步","坚","旱","盯","呈","时","吴","助","县","里","呆","园","旷","围","呀","吨", "足","邮","男","困","吵","串","员","听","吩","吹","呜","吧","吼","别","岗","帐","财","针","钉", "告","我","乱","利","秃","秀","私","每","兵","估","体","何","但","伸","作","伯","伶","佣","低", "你","住","位","伴","身","皂","佛","近","彻","役","返","余","希","坐","谷","妥","含","邻","岔", "肝","肚","肠","龟","免","狂","犹","角","删","条","卵","岛","迎","饭","饮","系","言","冻","状", "亩","况","床","库","疗","应","冷","这","序","辛","弃","冶","忘","闲","间","闷","判","灶","灿", "弟","汪","沙","汽","沃","泛","沟","没","沈","沉","怀","忧","快","完","宋","宏","牢","究","穷", "灾","良","证","启","评","补","初","社","识","诉","诊","词","译","君","灵","即","层","尿","尾", "迟","局","改","张","忌","际","陆","阿","陈","阻","附","妙","妖","妨","努","忍","劲","鸡","驱", "纯","纱","纳","纲","驳","纵","纷","纸","纹","纺","驴","纽","奉","玩","环","武","青","责","现", "表","规","抹","拢","拔","拣","担","坦","押","抽","拐","拖","拍","者","顶","拆","拥","抵","拘", "势","抱","垃","拉","拦","拌","幸","招","坡","披","拨","择","抬","其","取","苦","若","茂","苹", "苗","英","范","直","茄","茎","茅","林","枝","杯","柜","析","板","松","枪","构","杰","述","枕", "丧","或","画","卧","事","刺","枣","雨","卖","矿","码","厕","奔","奇","奋","态","欧","垄","妻", "轰","顷","转","斩","轮","软","到","非","叔","肯","齿","些","虎","虏","肾","贤","尚","旺","具", "果","味","昆","国","昌","畅","明","易","昂","典","固","忠","咐","呼","鸣","咏","呢","岸","岩", "帖","罗","帜","岭","凯","败","贩","购","图","钓","制","知","垂","牧","物","乖","刮","秆","和", "季","委","佳","侍","供","使","例","版","侄","侦","侧","凭","侨","佩","货","依","的","迫","质", "欣","征","往","爬","彼","径","所","舍","金","命","斧","爸","采","受","乳","贪","念","贫","肤", "肺","肢","肿","胀","朋","股","肥","服","胁","周","昏","鱼","兔","狐","忽","狗","备","饰","饱", "饲","变","京","享","店","夜","庙","府","底","剂","郊","废","净","盲","放","刻","育","闸","闹", "郑","券","卷","单","炒","炊","炕","炎","炉","沫","浅","法","泄","河","沾","泪","油","泊","沿", "泡","注","泻","泳","泥","沸","波","泼","泽","治","怖","性","怕","怜","怪","学","宝","宗","定", "宜","审","宙","官","空","帘","实","试","郎","诗","肩","房","诚","衬","衫","视","话","诞","询", "该","详","建","肃","录","隶","居","届","刷","屈","弦","承","孟","孤","陕","降","限","妹","姑", "姐","姓","始","驾","参","艰","线","练","组","细","驶","织","终","驻","驼","绍","经","贯","奏", "春","帮","珍","玻","毒","型","挂","封","持","项","垮","挎","城","挠","政","赴","赵","挡","挺", "括","拴","拾","挑","指","垫","挣","挤","拼","挖","按","挥","挪","某","甚","革","荐","巷","带", "草","茧","茶","荒","茫","荡","荣","故","胡","南","药","标","枯","柄","栋","相","查","柏","柳", "柱","柿","栏","树","要","咸","威","歪","研","砖","厘","厚","砌","砍","面","耐","耍","牵","残", "殃","轻","鸦","皆","背","战","点","临","览","竖","省","削","尝","是","盼","眨","哄","显","哑", "冒","映","星","昨","畏","趴","胃","贵","界","虹","虾","蚁","思","蚂","虽","品","咽","骂","哗", "咱","响","哈","咬","咳","哪","炭","峡","罚","贱","贴","骨","钞","钟","钢","钥","钩","卸","缸", "拜","看","矩","怎","牲","选","适","秒","香","种","秋","科","重","复","竿","段","便","俩","贷", "顺","修","保","促","侮","俭","俗","俘","信","皇","泉","鬼","侵","追","俊","盾","待","律","很", "须","叙","剑","逃","食","盆","胆","胜","胞","胖","脉","勉","狭","狮","独","狡","狱","狠","贸", "怨","急","饶","蚀","饺","饼","弯","将","奖","哀","亭","亮","度","迹","庭","疮","疯","疫","疤", "姿","亲","音","帝","施","闻","阀","阁","差","养","美","姜","叛","送","类","迷","前","首","逆", "总","炼","炸","炮","烂","剃","洁","洪","洒","浇","浊","洞","测","洗","活","派","洽","染","济", "洋","洲","浑","浓","津","恒","恢","恰","恼","恨","举","觉","宣","室","宫","宪","突","穿","窃", "客","冠","语","扁","袄","祖","神","祝","误","诱","说","诵","垦","退","既","屋","昼","费","陡", "眉","孩","除","险","院","娃","姥","姨","姻","娇","怒","架","贺","盈","勇","怠","柔","垒","绑", "绒","结","绕","骄","绘","给","络","骆","绝","绞","统","耕","耗","艳","泰","珠","班","素","蚕", "顽","盏","匪","捞","栽","捕","振","载","赶","起","盐","捎","捏","埋","捉","捆","捐","损","都", "哲","逝","捡","换","挽","热","恐","壶","挨","耻","耽","恭","莲","莫","荷","获","晋","恶","真", "框","桂","档","桐","株","桥","桃","格","校","核","样","根","索","哥","速","逗","栗","配","翅", "辱","唇","夏","础","破","原","套","逐","烈","殊","顾","轿","较","顿","毙","致","柴","桌","虑", "监","紧","党","晒","眠","晓","鸭","晃","晌","晕","蚊","哨","哭","恩","唤","啊","唉","罢","峰", "圆","贼","贿","钱","钳","钻","铁","铃","铅","缺","氧","特","牺","造","乘","敌","秤","租","积", "秧","秩","称","秘","透","笔","笑","笋","债","借","值","倚","倾","倒","倘","俱","倡","候","俯", "倍","倦","健","臭","射","躬","息","徒","徐","舰","舱","般","航","途","拿","爹","爱","颂","翁", "脆","脂","胸","胳","脏","胶","脑","狸","狼","逢","留","皱","饿","恋","桨","浆","衰","高","席", "准","座","脊","症","病","疾","疼","疲","效","离","唐","资","凉","站","剖","竞","部","旁","旅", "畜","阅","羞","瓶","拳","粉","料","益","兼","烤","烘","烦","烧","烛","烟","递","涛","浙","涝", "酒","涉","消","浩","海","涂","浴","浮","流","润","浪","浸","涨","烫","涌","悟","悄","悔","悦", "害","宽","家","宵","宴","宾","窄","容","宰","案","请","朗","诸","读","扇","袜","袖","袍","被", "祥","课","谁","调","冤","谅","谈","谊","剥","恳","展","剧","屑","弱","陵","陶","陷","陪","娱", "娘","通","能","难","预","桑","绢","绣","验","继","球","理","捧","堵","描","域","掩","捷","排", "掉","堆","推","掀","授","教","掏","掠","培","接","控","探","据","掘","职","基","著","勒","黄", "萌","萝","菌","菜","萄","菊","萍","菠","营","械","梦","梢","梅","检","梳","梯","桶","救","副", "票","戚","爽","聋","袭","盛","雪","辅","辆","虚","雀","堂","常","匙","晨","睁","眯","眼","悬", "野","啦","晚","啄","距","跃","略","蛇","累","唱","患","唯","崖","崭","崇","圈","铜","铲","银", "甜","梨","犁","移","笨","笼","笛","符","第","敏","做","袋","悠","偿","偶","偷","您","售","停", "偏","假","得","衔","盘","船","斜","盒","鸽","悉","欲","彩","领","脚","脖","脸","脱","象","够", "猜","猪","猎","猫","猛","馅","馆","凑","减","毫","麻","痒","痕","廊","康","庸","鹿","盗","章", "竟","商","族","旋","望","率","着","盖","粘","粗","粒","断","剪","兽","清","添","淋","淹","渠", "渐","混","渔","淘","液","淡","深","婆","梁","渗","情","惜","惭","悼","惧","惕","惊","惨","惯", "寇","寄","宿","窑","密","谋","谎","祸","谜","逮","敢","屠","弹","随","蛋","隆","隐","婚","婶", "颈","绩","绪","续","骑","绳","维","绵","绸","绿","琴","斑","替","款","堪","搭","塔","越","趁", "趋","超","提","堤","博","揭","喜","插","揪","搜","煮","援","裁","搁","搂","搅","握","揉","斯", "期","欺","联","散","惹","葬","葛","董","葡","敬","葱","落","朝","辜","葵","棒","棋","植","森", "椅","椒","棵","棍","棉","棚","棕","惠","惑","逼","厨","厦","硬","确","雁","殖","裂","雄","暂", "雅","辈","悲","紫","辉","敞","赏","掌","晴","暑","最","量","喷","晶","喇","遇","喊","景","践", "跌","跑","遗","蛙","蛛","蜓","喝","喂","喘","喉","幅","帽","赌","赔","黑","铸","铺","链","销", "锁","锄","锅","锈","锋","锐","短","智","毯","鹅","剩","稍","程","稀","税","筐","等","筑","策", "筛","筒","答","筋","筝","傲","傅","牌","堡","集","焦","傍","储","奥","街","惩","御","循","艇", "舒","番","释","禽","腊","脾","腔","鲁","猾","猴","然","馋","装","蛮","就","痛","童","阔","善", "羡","普","粪","尊","道","曾","焰","港","湖","渣","湿","温","渴","滑","湾","渡","游","滋","溉", "愤","慌","惰","愧","愉","慨","割","寒","富","窜","窝","窗","遍","裕","裤","裙","谢","谣","谦", "属","屡","强","粥","疏","隔","隙","絮","嫂","登","缎","缓","编","骗","缘","瑞","魂","肆","摄", "摸","填","搏","塌","鼓","摆","携","搬","摇","搞","塘","摊","蒜","勤","鹊","蓝","墓","幕","蓬", "蓄","蒙","蒸","献","禁","楚","想","槐","榆","楼","概","赖","酬","感","碍","碑","碎","碰","碗", "碌","雷","零","雾","雹","输","督","龄","鉴","睛","睡","睬","鄙","愚","暖","盟","歇","暗","照", "跨","跳","跪","路","跟","遣","蛾","蜂","嗓","置","罪","罩","错","锡","锣","锤","锦","键","锯", "矮","辞","稠","愁","筹","签","简","毁","舅","鼠","催","傻","像","躲","微","愈","遥","腰","腥", "腹","腾","腿","触","解","酱","痰","廉","新","韵","意","粮","数","煎","塑","慈","煤","煌","满", "漠","源","滤","滥","滔","溪","溜","滚","滨","粱","滩","慎","誉","塞","谨","福","群","殿","辟", "障","嫌","嫁","叠","缝","缠","静","碧","璃","墙","撇","嘉","摧","截","誓","境","摘","摔","聚", "蔽","慕","暮","蔑","模","榴","榜","榨","歌","遭","酷","酿","酸","磁","愿","需","弊","裳","颗", "嗽","蜻","蜡","蝇","蜘","赚","锹","锻","舞","稳","算","箩","管","僚","鼻","魄","貌","膜","膊", "膀","鲜","疑","馒","裹","敲","豪","膏","遮","腐","瘦","辣","竭","端","旗","精","歉","熄","熔", "漆","漂","漫","滴","演","漏","慢","寨","赛","察","蜜","谱","嫩","翠","熊","凳","骡","缩","慧", "撕","撒","趣","趟","撑","播","撞","撤","增","聪","鞋","蕉","蔬","横","槽","樱","橡","飘","醋", "醉","震","霉","瞒","题","暴","瞎","影","踢","踏","踩","踪","蝶","蝴","嘱","墨","镇","靠","稻", "黎","稿","稼","箱","箭","篇","僵","躺","僻","德","艘","膝","膛","熟","摩","颜","毅","糊","遵", "潜","潮","懂","额","慰","劈","操","燕","薯","薪","薄","颠","橘","整","融","醒","餐","嘴","蹄", "器","赠","默","镜","赞","篮","邀","衡","膨","雕","磨","凝","辨","辩","糖","糕","燃","澡","激", "懒","壁","避","缴","戴","擦","鞠","藏","霜","霞","瞧","蹈","螺","穗","繁","辫","赢","糟","糠", "燥","臂","翼","骤","鞭","覆","蹦","镰","翻","鹰","警","攀","蹲","颤","瓣","爆","疆","壤","耀", "躁","嚼","嚷","籍","魔","灌","蠢","霸","露","囊","罐"};
    private static final String[] email_suffix="@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn".split(",");
    private static int[][] range = { { 607649792, 608174079 },{ 1038614528, 1039007743 },{ 1783627776, 1784676351 }, { 2035023872, 2035154943 }, { 2078801920, 2079064063 }, { -1950089216, -1948778497 }, { -1425539072, -1425014785 }, { -1236271104, -1235419137 }, { -770113536, -768606209 }, { -569376768, -564133889 }};
    private static ArrayList<Object> scanFiles = new ArrayList<Object>();
    private static int count=0;

    /**
     * 生成32为随机字符
     *
     * @return id
     */
    public static String createSerialId(){
        String id = UUIDUtil.get();
        return id;
    }

    /**
     * 随机生成7~11位的随机数
     * @return
     */
    public static String createUserName(){
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int[] count = {7,8,9,10,11};
        int x = r.nextInt(5);
        for(int i=0;i<count[x];i++){
            int num = r.nextInt(10);
            sb.append(num);
        }
        return sb.toString();
    }

    /**
     * 根据userName生成邮箱
     * @param userName
     * @return
     */
    public static String createMail(String userName){
        int x = new Random().nextInt(email_suffix.length);
        String mail = userName+email_suffix[x];
        return mail;
    }

    /**
     * 随机生成2~7字昵称
     * @return
     */
    public static String createNickName(){
        int surNameLen = surName.length;
        int doubleSurNameLen = doubleSurName.length;
        int wordLen = word.length;

        StringBuffer sub = new StringBuffer();
        Random random = new Random();
        int x = random.nextInt(6)+2;
        boolean[] flag ={true,false};
        int y= random.nextInt(2);
        boolean simple = flag[y];
        if(simple){
            sub.append(surName[random.nextInt(surNameLen)]);
            int surLen = sub.toString().length();
            for (int i = 0; i < x - surLen; i++) {
                if(sub.toString().length() <= x){
                    sub.append(word[random.nextInt(wordLen)]);
                }
            }
        }else{
            sub.append(doubleSurName[random.nextInt(doubleSurNameLen)]);
            int doubleSurLen = sub.toString().length();
            for (int i = 0; i < x - doubleSurLen; i++) {
                if(sub.toString().length() <= x){
                    sub.append(word[random.nextInt(wordLen)]);
                }
            }
        }
        return sub.toString();
    }

    /**
     * 生成8-16位随机密码
     * @return
     */
    public static String createPassword(){
        int x = new Random().nextInt(8)+8;
        // 最终生成的密码
        String password = "";
        Random random = new Random();
        for (int i = 0; i < x; i ++) {
            // 随机生成0或1，用来确定是当前使用数字还是字母 (0则输出数字，1则输出字母)
            int charOrNum = random.nextInt(2);
            if (charOrNum == 1) {
                // 随机生成0或1，用来判断是大写字母还是小写字母 (0则输出小写字母，1则输出大写字母)
                int temp = random.nextInt(2) == 1 ? 65 : 97;
                password += (char) (random.nextInt(26) + temp);
            } else {
                // 生成随机数字
                password += random.nextInt(10);
            }
        }
        //加密
        password = MD5Util.encrypt(password);
        return password;
    }

    /**
     * 随机生成35-10年前的出生日期
     * @return
     */
    public static String createBirthday(){
        GregorianCalendar gc = new GregorianCalendar();
        Calendar cal = Calendar.getInstance();
        int nowYear = cal.get(Calendar.YEAR);
        int start = nowYear - 35;
        int end = nowYear - 10;
        int year = start + (int)Math.round(Math.random() * (end - start));

        gc.set(gc.YEAR, year);

        int dayOfYear = 1 + (int)Math.round(Math.random() * (gc.getActualMaximum(gc.DAY_OF_YEAR) - 1));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        String birthday = gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH);
        return birthday;
    }

    /**
     * 生成手机号用的方法
     * @param start
     * @param end
     * @return
     */
    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }

    /**
     * 生成随机手机号
     * @return
     */
    public static String createUserPhone(){
        String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }

    /**
     * 生成随机用户类型（管理员类型不在此生成）
     * @return
     */
    public static Integer createType(){
        int x = new Random().nextInt(2)+2;
        return x;
    }

    /**
     * 生成随机地址
     * @return
     */
    private static String createAddress() {
        int index=getNum(0,road.length-1);
        String first=road[index];
        String second=String.valueOf(getNum(11,150))+"号";
        String third="-"+getNum(1,20)+"-"+getNum(1,10);
        return first+second+third;
    }

    /**
     * 生成国内随机ip
     * @return
     */
    public static String createIp(){
        Random rdint = new Random();
        int index = rdint.nextInt(10);
        String ip = num2ip(range[index][0] + new Random().nextInt(range[index][1] - range[index][0]));
        return ip;
    }

    /**
     * 将十进制转换成ip地址
     * @param ip
     * @return
     */
    public static String num2ip(int ip) {
        int[] b = new int[4];
        String x = "";

        b[0] = (int) ((ip >> 24) & 0xff);
        b[1] = (int) ((ip >> 16) & 0xff);
        b[2] = (int) ((ip >> 8) & 0xff);
        b[3] = (int) (ip & 0xff);
        x = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2]) + "." + Integer.toString(b[3]);
        return x;
    }

    /**
     * 生成是否注销
     * @return
     */
    public static Integer createIfLogout(){
        int x = new Random().nextInt(2);
        return x;
    }

    /**
     * 随机获取图片路径
     * @return
     */
    public static String createImage(){
        String fileName = "D:\\gif\\head";
        File directory = new File(fileName);
        if(directory.isDirectory()){
            File [] filelist = directory.listFiles();

            for(int i = 0; i < filelist.length; i ++){
                scanFiles.add(filelist[i].getName());
            }
        }
        int x = new Random().nextInt(scanFiles.size());
        String fiLePath = (String) scanFiles.get(x);
        return fiLePath;
    }

    /**
     * 创建时间
     * @return
     */
    public static String createTime(){
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = df.format(new Date());
        return dateTime;
    }

    /**
     * 创建人
     * @param userName
     * @return
     */
    public static String createBy(String userName){
        return userName;
    }

    public static void main(String[] args) {
//        System.out.println(createSerialId());
        String name = createUserName();
//        System.out.println(name);
//        System.out.println(createMail(name));
//        System.out.println(createNickName());
//        System.out.println(createPassword());
//        System.out.println(createBirthday());
//        System.out.println(createUserPhone());
//        System.out.println(createAddress());
//        System.out.println(createIp());
//        System.out.println(createType());
//        System.out.println(createIfLogout());
        System.out.println(createImage());
//        System.out.println(createTime());
//        System.out.println(createBy(name));
    }
}
