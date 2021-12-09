package aoc.day9

val dayNineExampleInput = """
    2199943210
    3987894921
    9856789892
    8767896789
    9899965678
""".trimIndent()
    .split("\n")
    .map { it.map { c -> c.digitToInt() } }
    .let { Grid(it.first().size, it.size, it.flatten()) }

val dayNineInput = """
    7659991098999876579910129879999876432123459874345567890126678999876588975767899323456989767899432101
    8998789987898765467891239868899876541012398765123456789234567897643467894656798912399878948678944212
    9867678976789878598954398756789997632343459873234569898765679999856578943547987893989865434567894323
    7654567895896989679767499847994398755456579987656778969876892198767989652129876889876976725678965734
    8767678954345698799898987659943219876577694598787889545987931019879996543298965679965987438789876799
    9898789921239799898989899798794399987688965679898993534598942123989987654987654567894596549899989987
    4969999892398989987876789987689989998789898789979992123989653934598798965976543678943987678999999876
    3459898789497878976545678986567878999895679898768989239879869896789659879865432469432198799998789765
    2498765699976567995434389765434567899934989977655679356965998789896545989865321258921019999987678954
    3987654398765456789321238979325456799329898766434798999876797698987432198754310347894329789876567893
    4696543219876967998910147998214345678998789954323987689999977567898543479885541456789498678988678932
    5987654423987878987521236987601234567891678893219876567898765479987654567976632367899987569899789321
    6798767834698989997432345698524568698932456789398765466989876567898765689987545478959768456789893210
    7899898945679499876545656997434578799843569892987654345678989679999876799798658569349654367878964322
    8977999498789398987896769876545689998767678931999869656789698789999987987698767895498743212567895433
    9656789329898987899929879987676790199898989949877998767896559899878998977569978999987654323458986654
    8797995434987896989434989798989891987919499896765569888921434998767789865452989998998795434569997765
    9979899549876785678945995639698999876329398764354456999990125899545678954321299987899987895678949878
    9865678998765434567899894324567892985498999863212347898989436798434599876210389996789999998789434999
    7654567899896647978989789212459921296987899954393478987678945987324879965341567895679891019998959865
    8543878901987656789765678901268933459876999895989569658567959876412568897432467894599789923987898654
    9212389919898767897654577892999545998765798789878978945456899954323456789576578923989679899876789543
    9543457898769879986543456789889959876543987676567899432345789976436567897697989219878565678965678932
    8754568987654989765432347898767899989432976543456964321012498987545679998989892109867434699764567891
    9867689298543299986541034987656789998743989654677895632134987898696789989878789298754324789543488989
    1978792129654569987732129874543567897654799965789976853239876799989999878765678999865455697601245678
    0989893098765678998653298763212375789795679878994989966398754889879898765464569899976566789212346789
    9898954239987789998784987654301234699989789989873398765459765678968789884323456789987677894323456898
    8777895345698999899895698976214345789878999997762129876589896789345678965434567999898989976564568967
    7656976457899019767987899765423456789569899876543299987678987891234569876545898998769394987875679456
    6546899568992198657898939878534677893456789987654989898789398932347678999856789889843212398986989345
    5435688979879932545679929989665788902569991298779878789899299543458789798767895678932101569997890123
    4323567899767891434589898998776899543457899999898768655978987654569899679878934569643219878998921254
    6764579987658910123456797879887899656568978789987653234567998785678998532989545678954399989999432765
    7875689998767891234567896569998998967989767698798767145678999896789987643498756789765989992987543876
    8976796899878932545678997998769467899899854599659898657789985959897898784569867899876978931098954987
    9697895799989873467889989999652348999799965678943939768999664543956789895679878979989867892129895698
    4598954569899964578999878987643499997689978789432129879998543212345678976989989459899756789298789789
    3499543798798765699998767898984987843579899996583235989987632105468789987899992398789898999987678991
    4985432987679876789999658989876986532498798889874346798798743236589893498989891987678989459986568990
    9876521296567987899876545878989875421987656778965498987669654587679999999876789898547678998765456789
    9876432987678998998765434569998767410996545567896569896556965689789987898865676789435589765432369999
    3987543498799549769886325679987654329877434456789698789439879789899976987764545678923459879321287899
    4599656789989432458997212568999769498764321298999987688956989897999899876743236789212398998932456998
    9798767897678921367989323459998998999875210147899876567897891956789798765432145794301987897893569997
    8999878996569432349876534569987687898654321236789767456789932345679659976545012689419876896789878986
    7899989987458943499998765678976546789985434545678954345679543567789545987983234568998765645678989565
    6789199654347896589989876789987434579876545758789543237789654579895434499874356789329874234567893434
    5679298789756789678976987899874323459987859767899654345678965989965421298765667895499932125689932123
    4568999898969898789765698998765212398498869878998765656789879898965432399876878976987891034567894034
    3456789987898999899894329987654323987349978989999898767896999797896743987987899989896789123678985125
    2346899876767892999989212398765499876567989999899959878934987676989659876798945698765695434679876789
    1256998765458993498979903459876989987698999999789943989949898455678998765759899987654989545689989899
    4349879876569989986567894967989878999789989897678892099898789334569987674545678998743478957897696999
    5478968987678979765456999898998767879899876789546789298788699212989876543234567998932569768998565678
    6568956998789569876567897789999857867998975695437995987657598909898995432123459886521678989895434567
    7678939879892458987678956699896645456987764789567894696543467898767986321019598765432789496789323878
    8789998765901456798789545598775435349876543999698943495432356789656997432198969876547892345689439989
    9895987654312345789899434459654324234987875898789212989321234896549876545997656989856921234579998997
    6954398767433566789998921398743210125698986789894309878540145789432997859876543398767890123467897686
    5695999876544678998787892987654341234589987895999498765431236894320989767987432129898921294878987575
    4989899987698789987656789398765492395678998934998989896549898965999878979876421012969939989999098464
    2976789998789899876543789249876989989989239019887678987656789879878767898985432123457898679889198323
    9895678999894968987654590123989879978990129198764569998987894998765459987899543434568987546778987634
    8654547899923656798985891294598768969893298998765678999598912349821398795698976546699876435569876545
    6543336789012349899876789989987656756789987899976789989499909496543989654567898687987665523456987676
    7652125978923598987987899878996541234567896569899899978987898987859878965678998789876563212345698787
    6543234567894987876798998769876532345978943456799998767896987598998969898799989898765432101234569898
    7685346878999876765689987655989645567899212566778987658965398459987856789899978999876543212385789999
    8876798989998765434569976743498756789956401234568998789876999349876546899988769899987654525678999999
    9987899697989954323798765632359867899543212345679239899989899956997635789879656789999769434789898989
    4599996546567893212987654321235978998764637566989139999998789899986523998967545698999898945699787678
    3499989435457899433498765435348989689895547677891098988997655798765439897645634567899976896789676567
    2989978921345678994569876546757894578987678788992987677893234569898698765430125698999865689896543456
    9879868935458789989778987987868943989998989999789987566989123456989789876321234789997684578965432123
    8965657899599999878989998999979659899879596545678976435778934569879894987432545679876543467896673294
    7654545698989997569999999998989799789965432434789897324567895979964933498545789789987432456998784989
    6543234987978965478999899987899987656974321023498788212379976798763212379676899899997544567899999878
    5432129876568896567898789656789976549875432164589654323568987987654343456987895978999655778956798967
    4321019987456789678987698943495987678987543765678965454569998998765454567898954567898776789345987954
    5432998765345698989996587992976798789798765897789876875678999869876569878979943459979987893212986543
    6549879876234767899985456789897899997659897899897987996789998754997878989767892598965398994323497632
    7698765438123456789876323496789992198943998943956998987899987643298989997656999987890139989434598745
    8899954321014567899865212345678989989894989012345899498989998759109499998747988976789239878965987656
    9998765732125678999954345489789679878789876543456789349678939998912349876434567895678949867896798767
    4349876653346789998769497679898998765699998754678991234569019887893498765325658934589998758659999878
    4239999778659899899898989989967987854897899869789893965678998766799987654312349898699896549237899989
    9398999889767998789987978995459876543786789878898789896799987655678998895401256789798765432126789997
    8987899999879987678976567894398765432645699989997678789929876543767899986212868999899654321034599896
    7856789432989876569875456894298764321237989899986565678910987432347678997343479756998789532123456789
    6545678921098765454986587932129879854349876799867434568924596541234599987654569545689898743454567895
    5436889932129854323697698943234988765698765987654523567895987762345789598968678934578999654765678954
    4321968894298765634598789954445699878987654599763212348997898943579893459899789545678998765878789875
    5872456789349878745679897895768789989877653459894103456789999874568932598789897676789129878989899986
    8763878995467989856789976979879899998765432345989294567899899865679943987699998989899234989299999899
    7654567896568995977891234568999978919876521239878989678956756978989894976568999195978976790199899788
    8765698987878923988910123456789567923987432399765679789432347989998769876456789234567897892988698677
    9878789498999219899321256587995456894898643989813478997643458994987656987567894345679998999876545556
    2989893219879998765432347898932345995798759876524567899856969543499897897698987457989899298765434345
    1099954523467899876545456999545476789899899976435788923987897652101998998789876567897654349854321237
""".trimIndent()
    .split("\n")
    .map { it.map { c -> c.digitToInt() } }
    .let { Grid(it.first().size, it.size, it.flatten()) }
