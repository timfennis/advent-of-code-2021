package aoc.day8


val dayEightInput = """
    fgcae ebafc cabdef eg abecfg abgfed feg gafdc bceg ebgcadf | defagbc faecg cfdag gecb
    eagd cad fgadbc aefdcg dcebfg fcegd cbeaf ad dbgfeca defca | cfdeg gdcabf fcgde afgced
    gfdeca aeb eb fbdag eafdc adfbe cefdab bdaegcf efbc ecadgb | eb dbafe eab faecd
    facbdge efdg gcafd daegc caegb aecfdb ade ed gafbdc dgfeac | de abcdef faedcg dfgca
    dcaegbf bc dcgeab cbgad cebg cdfaeb fbadg acdge cefdga cdb | gdcfae gbec gdace cgadfe
    ceagfbd fbaeg fadge fbegad abgecf dafcg ed edab ged dfcbeg | gebfa de gbafec ed
    acedbf adfbg cgafedb geac cgb befcgd bcgaf bfgace cg bfeac | bcdfeg fecba cafgeb bgdfa
    egadc cea ac agbcedf cgab bgcade fbcaed eadfbg fegdc ebdag | fdecabg fcged cdbage efgdab
    cbfge gdc cdea efdag dc cdfeg gfbcad gcdeaf cdeagbf egfadb | dfcge egcfd fcdbag dc
    gaedb fbced gcdbef cge gc decgb dcbgfea cgfb cgdfea fbcade | fdegbac fecbd ecg degba
    geaf bcfedg agbcd bfeagd dgfbe cbdaef beacdfg abe agebd ea | fbecdg geaf gebfad adgbc
    bed afgebd adecf bafgedc dbca cfegb becdf fabdce bd gcfdea | gfdaeb ebfgad dbefc cbda
    gacfebd ag bedfca agbfde egfcab dfga degab fbade bag bdecg | dgaf abcfed agb bfecag
    afebg afdeg gcbe acebdf cfbage cafeb gab decgabf bg gcfdba | gb caebf abgef acfebd
    efdab fabdgce gcbfa eg gacbfe beg ecag beafg dfabcg bgecfd | bge fgdcbe gcfdba ebdaf
    aecbd bdagef gd bfega aefgcbd gfde gfdcab dbg edgba febagc | dg dagfbc dgfe eabdgf
    gdfba egfacbd gdcefa baf gcba fdagcb dgfac ab dcfeba ebgdf | dcfag bfa gafcd cbag
    afbe bdagc cedfb defcab ebfdgac gfedbc cbfad af adgcfe adf | dafbc dfbac fdcegb gadcb
    fcgedb ca fdcage cagdbef ceab acedfb fdbce cafdb cda afgbd | ac dcafb efgdac ebcfda
    fbad gbcda dcefgb cfeadg dgbcaf bcafdeg ad gcabe adc dfbcg | gbcdef bcfdg cgbfd bfadgc
    afcgeb bedag bdecafg agb bedfga fdagce bg bgfd dfgea cdabe | badge cagdfe efdag afegbd
    gfed dcaef ge bafdce daceg acebfg cefadg egc ecdabfg gacdb | bcgad bcegaf agecd degcafb
    deacb adcge ab acb fbdce faedbgc fgadec gabd deabgc ecbfag | dfbec ab cbagfe bcfgae
    gcabfde ecf cbdeg bacgf fedg ecfgb gfedcb fcaedb cebdag fe | dbcegf bcgdfe gfde defg
    daefb bfg gedafb fabge dbafec cfega bdga cgbdfe gb gbacedf | aecfg bcedaf begdcf dgba
    bagce bf bdacfeg efbg fcb cefab gbfadc cafebg ecdfa bacdge | fegb cegba bagce efbg
    becag cfeg cefgbad fbace gfeacb acdegb bef ef adbcf fdabge | ef bdfac cbegad gbeac
    egb afcbe fbaeg eabgfdc gcab gb gfdea fgbecd bcdafe gafbec | fcdgeb edcgbfa gbe fgeba
    dga caebd ecdag cegfd abdfce dafbge ecbgda bcdgefa ga agbc | afdbec ecfdab gdaec gad
    fc gbdefc cfe ebdaf fcbeda gbafde edacg bfcdgae dacef cabf | decgfba cdbefg cf ecdabf
    febgd dafgbc caed cgd ceadgf fbecga dcgfe aegfc gbedfca cd | dgfeb fgced dcafebg agdfce
    ecdafgb bgade bgd fabd gbaef bd egadbf ceadg cfbgea dgbfec | edagc bdage bfda dbgae
    cgeba aefgcd dfceagb dc ced facbed cdfb dbaec adfeb fgedba | fbdea bgcae efbda fcdeag
    caegbf dcfga afcedgb afgdcb fcedag ab edbcg dbfa gdcab abg | bag cbdag agb ab
    gadfecb befc fgdbea cf fedagc fac bcgfa agbfe adgcb feagcb | gbcda cgbad bcgfa cf
    dae aefcb dgabec dacfg afebdc ebfd afecgb aedcf fbcagde ed | ecabfgd bedf bcadef egdacfb
    abcf fcega fdgce fegbda eafdcgb ac gabecd gbfae beagfc eca | edbgca edbfga cae gface
    bgedcf afcdbg ag gcbaef fcedgab afgdb cfgbd befda acdg agf | fdbga ceadbfg egfbac fag
    bad edafc bdeg abcgde ecafgbd gfadcb bgaec db edcba geafcb | cdafe fdcabg cdfageb dbge
    geb bgcf agfcde gb befag gfbcae defab gabdec eacfg gabecfd | afbedgc gceabf abgef egacf
    fdeac acg cfegda cbeadf fegdbca ag acefg adge bfdgca gbcfe | gecdaf efgca cgbafd cfgeb
    begdf bdfgc ebcf adfcg cdegbf dgeafb cbg cb bfeagdc gbdcea | acgedb fagdbe bgfde dfgac
    afcgdb aedc bedgf egafcd afdge gecaf fcgebda gda da ceabfg | gedbf gacfe adg agdef
    bdeafg acgebdf dgcfa fa cafe dgfbc gaf edagfc cgabde agedc | fadbge bdgfcea dgcefa cgeda
    gacfbde defag cbgef gbca efadcb afcbeg cgbdfe ba eba gefab | fegab abe ebfcgd fbgae
    gfbdec fbcade cabdegf ebdg bfd dcfgb cadgf gebcf db fcabge | eacdgfb bdge ebdg ecafdb
    egcfba fdcae fcdaeb gcfdae fabd ba cdeab fcdbaeg eba decbg | cabed facbed cbade dfcea
    agdfe acfdeg fcdbeg dgf gdaec df cadf ceagbfd abgfe gbdaec | bgcdef abfge gbefcd df
    dgebf afgdb bfa fcbade af dcafgb agcf gcbda dabgecf gadecb | dbcga abcdge fadgbce eagcbd
    dbcga bea egacdf dageb be efbd abgfce gadfe fcbegad aebfdg | eba be bdage bedga
    gcbd egbdafc fgc fbgedc gc aedbfg cefgb fedbg cfaegd efabc | bface egfbc bgdacfe gc
    fdgae dc ced gbcd gbcefd decfba becfg ecfabg cgfde gbcadfe | edbcfg cd cafgbe cdegbf
    geadcf efg gadef fg fecad acbgfe dagecbf dcgf gadbe afbdce | edafcg bgcfade gfdc abedg
    edcba ecgdb aedbcf aebf dabfc fdacge badgfc ae ead gfcbade | gcebdaf degbc ebfadc efdcba
    caedbg dbgec dcbf ecgdbf cgdef dgf bgaedcf egacf afebgd fd | cdfbeg ecfbgda gcbfed cbgedf
    bdag acdeb ecgdb egbcfa fcdbega gb dbecag cbdafe ebg gdcef | bedgc abdfcge efagcb cdgef
    bfade bfdga dfbgec cagbfd dfgacbe gba gacf ag gdcbf abgedc | cfag abdcge ebafd abefd
    agcdef bcfda bdgca aebdfcg eagdc gb dbg gbea cedagb bfgdec | dfabgec eafdgc acdge gdecab
    ebacdfg cgbfad egdbc bdgfa daef bae gabed febadg ae gacbef | fagdb daebgf fgdab bea
    dcgbf cbedf degc bgc fcgdbea fbadg cfdgbe gc efacgb fdbace | edfcb cfeabg cg edbacf
    bcdagfe dfacb cbfdge bgcdf fdgeac acd agbd acbef da dcgfba | cbgdf dgab ad gbdcaf
    edgfbca gbe be ceba bdaecg gefcd dbagc egbadf dcegb dfgabc | gcfde gfceabd gfbcad eagfdb
    dfcbg fcdga efgb gecbfd gecbda dfbgaec bedfc dgb bfadce bg | dbcgf bfge dacgf dfgcbe
    cga ebdgaf bfdag cbgfe bfadgc gbfeadc ac cagfb cdba cgadef | bfdag agedcf aegcbfd gcfab
    gcead gfdbec cbdge gabfce eb dfgcba ecfdgab befd dgcbf ebg | cdgae fbdegc bcdge gcdfb
    da ebgfda fcdeagb acgbfe gedba dbegc agdecf dae bafeg bfda | gdafec fegba edfbga aed
    fbagec gdfba bcg gebcfd cbafg bcdfgea ebac cefag faedcg bc | gfbac baegcfd fabegc dgeacbf
    dfbeag fabdg fdbe abdeg eb dcega gcefba cfdbag dgcfbae geb | cgbafd bdef dcage eb
    abdcfe gba dagf cagbefd fdbea bfega ag dafbge dcbeag gfceb | afbcde ecgfb dfbega bdgeca
    dg dge gdbc cfdae bcgae cbdage abgfde cfedagb cdgae eacgbf | eabgc bgeac bcdg cagbe
    bfd dgaeb gcabde fd bfgce dgfa dbefg gadbfce dbafge fabcde | edfcba efbcg df bdf
    gcbfd agbecd fedc edcbfg fdb eadfbg bcfag fd gedcb gbacdef | fgbac bdagfec gfbdaec aegbdc
    cgbdfe ce acedg adcfeg bcfegad fcea ceg abdeg afbdcg fcgda | ecg aefdcg dacge ec
    egbdfca efacb agcdbe cbaed bdafgc dfaecg debg bd bda agced | geadbc efbca gabfdc bd
    edcfb bfgecd ecabfd eca abcd fgeab fedcag abfedgc ac bfeca | bgfae eca cdgfeb bfaec
    cadef cebdga ebd bacdfe dagfec dbfa fcebg ecbgfad db fdecb | befcd acgdeb abdf dbfa
    gbfecda bfd cdagb fbdacg fgdbec gbaf cbadf aefdc eadcgb bf | adcgb dfbca agbf dafcb
    abedg acbgf fd gaefcb cagbdef cdaf dcbfeg bdagf dgcbfa bfd | gbadcef df fadgb gabcdf
    fbgadc dbcfg dcega cgdfe cfedbg caefgb ef gef gfaebcd fedb | fcedg febd gbcdfa cdgfab
    bg adgb fcaedbg cgfda fcbae bcg dfacge fgcdba decgfb bcagf | cgafb dbfcage bdgcfe dbga
    ed afbdg acefb aecbfg cfdabeg gedfcb fcdabe ecda def ebdfa | gbafd gedfcb dfegcb dfgba
    gcdfae efgdb abdfcg fde gdabf dbcegfa fedgab efab ef ebdcg | eafb gbfda gacedf cbdge
    edagfc gfaecbd bgced cbegfd cb gefdc badgfc bdc efbc adegb | dgbec cegafd dcbgef dgecb
    gedcab beg eg cafgb fbegc gdfe bdgfec gabcedf bdafce bcdfe | gcefb afcbed ge gdfe
    fdbagc cdgefab edacf ceba ea cfbaed ecfdg abfegd adfbc eda | ea beadfc abcgdef ecdgf
    fbc gfbacd fecda cbdfa dbgf dceabg gbcdfea efbgac cdabg bf | gbdf agbced gaedbc gfdb
    aegcb bfgecd fbegad fcagdbe dfgce fcbd gdeacf fb bgf bgfce | bgafedc gcdaef dgfcbe eadgcf
    def dbce agdfeb ed fdgbcea dgcfb aegfc dfgbac gecfd cdfgbe | cdfegb ed cdgfaeb ebdc
    ecbfad afdb eagbfc caebgdf edfcb dgfec cgdbae bd bcafe bdc | bfacegd fbdcae dbc bgfdcea
    dfgba eg cfbead gde bfedgc dceab cabfdge gdbae aecg aedgcb | egd adebg gde debcfa
    cadfb egfb afgbc adbegc ecgadf ecagb fga ecfgab bgacfde fg | afcbg cgfba gf dacbge
    cegbf dec efcdg facdge dfae dcbafge ed adgfc fdbagc cgaebd | dce gbcfe afcdgbe dcgabf
    cfabe fa eabgfc cgaf fba gfbce dcfbge edbgcfa efbadg acebd | cfebga bfa fedcagb abfce
    acdfb dbagf afcebgd beag afgedb gfdea gbd fgdcbe gb cdeagf | dafge gafed gfacde gb
    gdfeac dfgce ebfadcg edagc dacf baegdc fd gdf egcfb dabgef | degfbac cfdeg daebgf cdeagb
    fcgabe ebcd ecfdg dabcgf gfecabd cfd ebcfg dc bcfegd adefg | agedf cd bcefg dc
    cbaeg bcfea fb feb gfaceb gdfbec dagbec gfab deacf bgcaedf | acbfge bf afgb efb
    egdba cadgfbe efadbg cg begc bfgdca caegd abcegd dcg acdfe | fbegcad bceg gcd cdgebaf
    agbcd dafcgb cfdg ceadb fdgeba eafbcg gfdab gbc bgdcafe cg | gfadcbe fbagd decba gc
    gfbec adgc dge egdcfa abgfde abgdfce dg eacfdb fdaec gdfce | fecgd gd fgcbe acdef
    fdbceg bfedc af fegcad cbdag daf efab dabcef fbeadgc cbfad | fcabd cbfad gcfdbe gadbc
    ebgac fabdeg bedca cg aegdcfb geacbf ceg efgba facg fbedcg | gecdfb aebfg fagc ceg
    eagb dcgeb ba abd cbeagd cdgafb fecbdga fgedcb dacfe dacbe | bdfeacg cadgbf bgedfc fcdae
    dbac cfgae cbfgde ba bga gcfbad dcfebga gcdbf feagbd cgbaf | cgafb fagbdc dcfbg afgebd
    adbgfe fgb egdafc cbafd dfegbc edgafbc egfcd cegb gfcdb gb | bg fdacge efcadg egdfba
    agfbe cgaefdb gbdf edagb afg cdgafe gfedab fg egcdba fabec | fcbdega fag agf agf
    cae edabfc cdfega ca cfba gadeb fdecb ecagfbd cbgdef acdeb | facdeb fgecad bfadce efcgbad
    facb dbfec dceabf efgcd cb aecfbgd dgfbae eafdb ebdagc bcd | bfedc cdegab cbdfe adefb
    fecbga badgc dga bfgadec dg geacb cgedba dfebag gcde fcabd | decg gda baceg gd
    gecbd gcbdfea cbfg bg bge egbcdf dcbef beafdc cdeag bfgeda | agcde dceag gfabde beg
    gefba gacfbe facdeg cg cgab gfc efbdcag cfgeb cefbd afegbd | gfc afbge dafegb aebcgf
    egd aebfgd gefc ge gedbfca bdcfe dfcegb gecdb edbacf cdbag | dacbg edcfbg bcdfe ge
    df ebdga gcdefa cfbd bgdfa afgcb dfeagbc bfacgd fgd facebg | gaebd gecbaf fdcb dfbag
    fag cegfdab cadebg dabcgf abefg eafc ecafgb ebacg fbdge fa | ebfag dbcgfae gbecad fdbgac
    gecbd gefa gfc fabdcg bfaecd ecgafbd fg befgc efabc gecbfa | gfae gcf egbdafc edgbc
    bfgae fcebag fdbe fd fedag adgfbc fdbgea ecagd agcbfed fad | fgabe dfeb ebfd gefba
    ecdgfb afge bdage ebcda eg abdgef fgadb gabcfd dge bcdagef | gdebfa eg agfbdec afeg
    dbc acefd cegadbf fbcde afcb fdcaeg bc acdbge febdac debfg | ceafd caefd gfbaced dcb
    fecgb fgacd gdcefba de gbafce febd gecfd edc fecdbg ecbgda | becdfg ed becgad gcdeba
    agfdbe fbac bcd edacfb bgecfd cb cdeba cfgdeab acged edfab | cb dbc adbce bdfae
    dgacef bdfa ecgbf ab fegba dgafe efdbgca abg acebdg afbdge | gefda ecgafd ecfbg begcad
    gbfcea bgfa afdebc aegdcb af bgcdfea bgeac gecdf afc fgace | bfgecad fca afc abfg
    cfe dgcfe fdagcb gebdc egfa gbcdefa dcebfa fe agdfc fgaedc | dfgac fe fce fcdbega
    fecgab gcbdef cbaefgd dfgbe dgcfe eb bfe dbfga edbc acfegd | fbdeg fdgeca agefbc egcfd
    aecd bac dfacbe cafdb ac gefcab gfcdbe fcbde gbdaf cbdegaf | gcdbefa fbgcae ecad fagecb
    eagbf cagedf geafdbc fdgbce adefb aecfd adcb deb faecbd bd | ebd deabf fadcge cdagfe
    gadefcb bfcga eadcgb egdbfc deag ecg edabc acedbf eg abgce | gdea bagce gce gce
    agcfe bec gecab gbeda facb dbfgec fcegab gfacde gfdaceb cb | cb cefdgb cadefgb bc
    fdgb df egabcf fegda cedga fegab efdbac def cgdefba gafedb | facdbe bafceg agedf fdebga
    fdaecg dbcafg abfgec dc caefg cedg aecfd abdgfce eadbf fcd | cgefab gbfacd fbegac fabecg
    dfecabg bcfeag gdafce dagbfc cdefb dca gafbc dbag da bcadf | dca bagfce cbfda cdagbf
    dabfgec febadg gbcdae gfbe adebf ef cabdf edfgca ebgda eaf | gfacbed abgced egdfca egfb
    cfdea dc fdagebc fegcbd agdc gcfade fcabe cfd efdgba gfead | bdfcge agdc dgefa cfd
    gbfca febadcg bc bdegcf beca begaf gabedf cgfbae cgfad cbg | bcg dcaefgb dfgceb afebdg
    cg cgbf bedcg fcagde adefbcg efdgcb ebacd cgd edbfg egdafb | cfgedb bgcf acegdf cfgb
    bfad adcgbe faceg fagbdc df afdgc efcbdag dfbegc gfd badcg | gfd abgcd fgd abfd
    bcadgf cgdfb adgcb gf gafc dcfeabg bdagce dgf dfbega cebdf | adcbfg gfca fdcbe gfac
    daebf fcabgde bgdcef gab ga edbcga afcg ebfga aefbgc egbcf | ebcfg ebcgfa fcgebd agcf
    gedab fg afgecb cfgd feg cfabed befdc fgdcbe gdceafb befgd | ecafbg ecfbd febdc gf
    gcbadf ce gfcdb fec feabd gbecdf dbcfage febdc agcdfe egbc | bafed gcbe gefcbd egcb
    cbdfg gbcafd feb gaecf gcbfe gfbdae be fcgabde bdgcef becd | abgedf gdfebc aedgfb fbe
    edacgbf fbdae edgac bg gab bagdec dagfce fadbgc cebg bgdea | aedgb bg cebg adcge
    bda gbfea gedcab gfbceda bgdfa bfcdg febcdg bagcfd cfad da | dbecag bgfdc gdbfa facd
    ceabd dabfe acgde bc fbca cgbfde febgda dfecagb ebafdc bdc | cbdae dbfea fgdceb gbfedc
    acg fadbgce cfgdeb dbgce efdca ag dbga cagbef egacd cdgbea | gdecabf gcbfea ecbdg gac
    dfgacb ged egfdab efcgbda gcefbd debga ebadc ge aegf gfdab | cdfgab cdafgb bfcgda ge
    gdbeca bcfde eafg cefbgad dgafcb ge degfb adefbg dgfba bge | bdfeg begdf ebfadg eabdfgc
    cfbdgae gbaec bagfcd gfcba dcaeg bacfeg dbeacf bec efgb eb | ceb egcab cagfb dgcae
    fdcagb gdaecb fcadg bcdgaef gd dfcea gbcaf aecgfb cgd dgbf | gfadc gfdb badgfc gbfca
    bcafed defgcab fgcb cfbae cagde agebfc gbcae gbe gadefb gb | bg gfcaeb bg aedgc
    bcfadeg gbfd eacfbd gdafe afebg bafegd geafcd gebca fb bfa | agdefc eafbdg fgead gedaf
    fcgeabd dgeaf ebgfad gceafb geadc ac aec ebcdg cafd adgefc | gbfadec egcbd acfd dgabef
    eac fbdgec ea eadb cbaeg agfdecb eafdgc dcgbe bcfga adcbeg | aec abde eac ea
    cedb gcadbe ed edgfbac fbega abdge gacdb fdacgb dcefag aed | cdagb gbcafd gdefac gedafc
    cae agfbec ae begcd feab fadgbc ecfadg ecabg cdgbefa cabfg | ecgba afcgb efba dgeafc
    fecgabd badf efadg abedfg fea fdgceb fedgb aebfgc af gdcae | ebfgca fae fea af
    bgedac gbfae dcbefg acbf fb gaedf cagedbf aebgc gebfca fgb | aedgf fdgea bgfae bfdgeac
    bagcfe eadcb gb facegdb bgc fdcag efbcad beadgc bgadc ebgd | gbc fbcage becad gbfcae
    cagde gbca dcgbea gc efcad dbgfea dfgecb caedbfg adgbe cdg | gc egdfbc bcga cg
    efgcbd aebgcd dfe eadfb abged abdcefg bacfd ef bdfaeg faeg | fgae cgdaeb adefb afcbd
    cgbfea gcbda gefdcb eadf febag bdf fd febgad fadbegc bgadf | gebcfd fgdbea dfb dbf
    dg cdgfe gecfad dfbce bgefda fgd eafcg efcbag dgac bcdagfe | afdebg gfd aecgf gfcea
    edfga cgefda gcdbe fbae ab egdabf cfgbda deabg bad adefcgb | ebadg agdeb fbae gbdae
    gbdcfa gbade ebgac ecbfa adfgbec badecf bgc gcef fbgace gc | gc fcge gacefdb ebgca
    acefgd ebgaf egfda egcd bgfaecd dgcfa ed abcefd efd abdgcf | def ebgaf fed befcda
    fecag cag cfagbd agfcbde fabec eabg fdcge cgbfea fadebc ag | cfgbda dfgbeca efdcg dfgcaeb
    bgecfda ecdag dgfcbe bdgac adbgf afgdbe bfac gdcabf cbd bc | bc acbgefd fagcdb fcba
    ba efacg cfbgde cfdbg bacgf bgfdea abf bcad cfbeadg bgcfad | bcdgef fdgaeb ab cagfe
    cbdaf bafdgc eacbd fd dcf fcabg bcdfeg bedgcfa dgfa agbecf | dfga bgfac df agdf
    edcabf cadbf bgfdcea bgcfad bgcde dfg fg bdfgc cgadfe abfg | adfecg gfbcd acgdfeb cbgdaf
    badgfc dg bagcde bceda dbcfea beafg debag adg fcbadeg edgc | gebda dcagbf dceba egfab
    edcf dacfbg dbcefag fbd df bdegfc ebdagc bgfea begcd bfedg | dgebc cbgde dgfbac bdf
    bdcfg acegfb acebfd fdcag egadcf da daf cgeaf edag bafcegd | gdcaf fgaec bafdec fdaecb
    cagefd fde gdfba fcgaedb abefd ef dafbeg ebfg afcbgd ebdca | egdbfa bceda dfabg bdace
    fcgb gcdae bg gfadeb cbfegd fcabed bdg gcdbe fdcbe adbefcg | dgb gfbdec befdc gfbc
    fabde bgfde bgecda cedgb gf cabfdeg ebfdgc fgbeac fcdg gfb | fgbde efdgb edgbc afedb
    ebacf cedfab fde eabfd fd baecgf bagde agdebfc dfcb egfadc | ebfdgac acdegf abdefc ebfad
    gdcb cd cfgae cgbedf fbegd efgdc fadcbe gfeacdb dce dfagbe | gbcd cde gacfe gefdbc
    geb afedgb bfegca bgade eg dgef gadbc eafdb afebdcg fcbead | cagbd defcba dbafec geafcb
    fgbdc gbfade gba gefa dbafg cbadge bfgecda ga edbafc dfabe | afbdeg dgfba bgdfa dcfgb
    agef badce ef febad cabfdg gdfab cbgeafd egfbdc defgba fbe | abefd geaf gafdbe ebcad
    gd gad bfdg gecba fedabcg fcgeda aedfbc adebf agbdfe dgeab | ebcdaf agd gcfbdae egacb
    cedbfa ba cgbfa abf dfegac cafeg gabe befgca ebdfcag cbfdg | fab aegcf faecbd fdcebga
    daefg febdga aegfdbc efgdca eafb bge eb agbdec febgd gbdfc | gbcfd edbfg egb bacegd
    fbdecg bdafec cfaeb gedac def dceaf abfd fd gfecba gfdaecb | ebfac bdfa gbcdef fd
    fcbga cbdfeag bafgce fdacg dc fcd fcgbad fadge efcdgb cdab | gaefcb bafegcd acdb abcgf
    ecgabd acgdf abge ae bcfead fgdaceb ead daegc bcdefg gcbed | egba gaecd cgadbe gdbce
    aegfbc gf afebdc agebdfc ebcdf feg dcefg bdfg dcgea bgedcf | edbfcg fdgb bdgafce febgcd
    dcg bcdefa dbfceg fdeacg acbeg bfeagcd cfdae dg dfag agdec | dfcgea dg egdca edagc
    agcfeb fagbed fdbceg bag gebfd afgd agbfcde acbed ag badeg | cbdea fcedgb gfebac abcegf
    adbegfc fgdace cadge afcg ebdfca ebgad eac bfgdce cgefd ca | acged fcedg ebagd dgacefb
    gfeadb gfeab bcfa agebcfd aebgfc fecgbd efc fc gadec egcfa | fgbace fc debgcf afgceb
    bacg ab cfgbd bfadge acfed fgdecb agedcbf fcabgd bfa dbafc | gfdcb cbga abcg afecbdg
    ge beagc cfgabd bgaced bgcda fbadgec age ecdagf egbd fcbae | aeg gedbac cadfeg fbgcda
    dgbafe abfdgec gdf fd agebcf gadec dfcgba befd edfga aebfg | abcgefd dagec dfg df
    edfcga bcegd baeg cbgdae agdbc gce acgdebf eg gfadbc cefbd | dcbeg bgedc cedbg edcbg
    adfbe dg cagef dfgb gdbeca aefdg gda edcbaf fdgeba gdecbfa | cdabef eafbd bafcegd dg
    cd edcbfg dbage fcaebdg facd bgcfea afbedc afbec dbc adecb | adegb bcefag abegd cbgedf
    gdcbf da ecgbfda adc adgb bagcfd dfbcea egfac dfbceg gdacf | gdba cfdbg adbfec fcaeg
    bcg bc gdcbae dbca abfgde gdeba ebfacdg egcba faceg dgfbce | geadb badecg ecbfgd baecg
""".processInput()
val dayEightExampleInput = """
    be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb |
    fdgacbe cefdb cefbgd gcbe
    edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec |
    fcgedb cgb dgebacf gc
    fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef |
    cg cg fdcagb cbg
    fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega |
    efabcd cedba gadfec cb
    aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga |
    gecf egdcabf bgf bfgea
    fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf |
    gebdcfa ecba ca fadegcb
    dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf |
    cefg dcbef fcge gbcadfe
    bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd |
    ed bcgafe cdgba cbgef
    egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg |
    gbdfcae bgc cg cgb
    gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc |
    fgae cfgab fg bagce
""".processInput()

data class Note(val patterns: List<String>, val outputValues: List<String>)

fun String.processInput() = this.trimIndent()
    .split("|", "\n")
    .map { it.trim() }
    .filter { it != "" }
    .windowed(2)
    .filterIndexed { index, _ -> index % 2 == 0 }
    .map { Note(it.first().split(" "), it.last().split(" "))  }