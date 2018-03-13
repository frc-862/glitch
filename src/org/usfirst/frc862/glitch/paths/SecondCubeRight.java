package org.usfirst.frc862.glitch.paths;
 
import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory;

// max path velocity 105.05009304258486
public class SecondCubeRight extends org.usfirst.frc862.util.DynamicPathCommand {
    private static Path path;

    public Path getPath() {
        return SecondCubeRight.generatePath();
    }

    public static Path generatePath() {
        if (path != null) {
            return path;
        }

        int num_elements = 80;
        Trajectory left = new Trajectory(num_elements);
        Trajectory right = new Trajectory(num_elements);

        build_segments_0(left, right);

       
        path = new Path("SecondCubeRight", new Trajectory.Pair(left, right));
        return path;
    }


    private static void build_segments_0(Trajectory left, Trajectory right) {

        left.setSegment(0, new Trajectory.Segment(0.007942936075598608, 0.7942936075598608, 
               39.71468037799304, 1985.734018899652, 5.759602046071822, 0.02, 226.33920867886698, 96.96433862147298));
        right.setSegment(0, new Trajectory.Segment(0.007942936075598608, 0.7942936075598608, 
               39.71468037799304, 1985.734018899652, 5.759602046071822, 0.02, 213.67454901282562, 75.02771865523057));


        left.setSegment(1, new Trajectory.Segment(0.03969274380171843, 1.587490386305991, 
               39.65983893730651, -2.742072034326526, 5.759603778134891, 0.02, 226.36670507590352, 96.94846416272144));
        right.setSegment(1, new Trajectory.Segment(0.03973661695925275, 1.589684044182707, 
               39.769521831142306, 2.742072657463268, 5.759603778134891, 0.02, 213.70208340549047, 75.01182226052266));


        left.setSegment(2, new Trajectory.Segment(0.1110231546887503, 3.5665205443515937, 
               98.95150790228013, 2964.5834482486807, 5.75961609663453, 0.02, 226.42847982387266, 96.91280034309058));
        right.setSegment(2, new Trajectory.Segment(0.11137905544212556, 3.5821219241436406, 
               99.62189399804669, 2992.618608345219, 5.75961609663453, 0.02, 213.76412838093583, 74.97600243341871));


        left.setSegment(3, new Trajectory.Segment(0.22958063173694282, 5.927873852409625, 
               118.06766540290154, 955.8078750310706, 5.759662410420134, 0.02, 226.53115657644054, 96.85352674149469));
        right.setSegment(3, new Trajectory.Segment(0.23110966067952649, 5.986530261870047, 
               120.2204168863203, 1029.9261444136803, 5.759662410420134, 0.02, 213.86782112324084, 74.91614232129236));


        left.setSegment(4, new Trajectory.Segment(0.39488077179149883, 8.265007002727799, 
               116.85665751590872, -60.55039434964087, 5.7597809668709194, 0.02, 226.67432143288468, 96.77089526939487));
        right.setSegment(4, new Trajectory.Segment(0.3994128356307135, 8.415158747559351, 
               121.43142428446522, 60.55036990724645, 5.7597809668709194, 0.02, 214.0135868871109, 74.83200968326152));


        left.setSegment(5, new Trajectory.Segment(0.606255877147698, 10.568755267809959, 
               115.18741325410797, -83.4622130900378, 5.7600244858758565, 0.02, 226.85741014272975, 96.6652640264238));
        right.setSegment(5, new Trajectory.Segment(0.616956277366505, 10.877172086789578, 
               123.10066696151134, 83.4621338523057, 5.7600244858758565, 0.02, 214.20201850789084, 74.72329596134703));


        left.setSegment(6, new Trajectory.Segment(0.862864636501732, 12.830437967701696, 
               113.08413499458689, -105.16391297605381, 5.760459395401115, 0.02, 227.0797222558261, 96.53710248871515));
        right.setSegment(6, new Trajectory.Segment(0.8845812949076798, 13.381250877058736, 
               125.20393951345791, 105.16362759732871, 5.760459395401115, 0.02, 214.433874588461, 74.58963254856495));


        left.setSegment(7, new Trajectory.Segment(1.163703529796069, 15.04194466471685, 
               110.57533485075766, -125.44000719146169, 5.761164930929088, 0.02, 227.3404378926269, 96.38699787037808));
        right.setSegment(7, new Trajectory.Segment(1.2032914027522512, 15.93550539222857, 
               127.7127257584917, 125.43931225168947, 5.761164930929088, 0.02, 214.71007809119632, 74.4306112986672));


        left.setSegment(8, new Trajectory.Segment(1.507619307959041, 17.195788908148607, 
               107.69221217158788, -144.15613395848865, 5.7622321499454605, 0.02, 227.6386362187719, 96.21566246611565));
        right.setSegment(8, new Trajectory.Segment(1.574239837310279, 18.547421727901387, 
               130.59581678364083, 144.15455125745638, 5.7622321499454605, 0.02, 215.03171587888747, 74.24580904048241));


        left.setSegment(9, new Trajectory.Segment(1.8933218930187983, 19.28512925298787, 
               104.46701724196305, -161.2597464812417, 5.763762913250507, 0.02, 227.97331465901908, 96.02394176886183));
        right.setSegment(9, new Trajectory.Segment(1.9987166530857636, 21.223840788774233, 
               133.82095304364228, 161.25681300007244, 5.763762913250507, 0.02, 215.40003972193557, 74.03481587999333));


        left.setSegment(10, new Trajectory.Segment(2.3193971000905664, 21.303760353588412, 
               100.9315550300272, -176.77311059679255, 5.765868880122821, 0.02, 228.34340790304364, 95.81282314626158));
        right.setSegment(10, new Trajectory.Segment(2.4781359911438416, 23.97096690290389, 
               137.35630570648283, 176.7676331420276, 5.765868880122821, 0.02, 215.81646918423618, 73.79726713840107));


        left.setSegment(11, new Trajectory.Segment(2.784318719432767, 23.246080967110036, 
               97.11603067608117, -190.77621769730158, 5.76867055413739, 0.02, 228.74780585363345, 95.58344483497096));
        right.setSegment(11, new Trajectory.Segment(3.0140239900050747, 26.79439994306166, 
               141.1716520078885, 190.76731507028342, 5.76867055413739, 0.02, 216.28259662961938, 73.5328788784801));


        left.setSegment(12, new Trajectory.Segment(3.2864596881262327, 25.10704843467329, 
               93.04837337816262, -203.38286489592718, 5.772296400597679, 0.02, 229.18536982044512, 95.337104973421));
        right.setSegment(12, new Trajectory.Segment(3.6080075991214797, 29.699180455820255, 
               145.23902563792976, 203.368681502063, 5.772296400597679, 0.02, 216.80019432625008, 73.24148712783925));


        left.setSegment(13, new Trajectory.Segment(3.8241023604164384, 26.882133614510288, 
               88.75425899184997, -214.70571931563285, 5.776882035179147, 0.02, 229.65494649575174, 95.07527031361576));
        right.setSegment(13, new Trajectory.Segment(4.2618042934107265, 32.68983471446236, 
               149.5327129321051, 214.68436470876782, 5.776882035179147, 0.02, 217.37122329352923, 72.92309109144486));


        left.setSegment(14, new Trajectory.Segment(4.395448224742312, 28.567293216293677, 
               84.2579800891695, -224.81394513402364, 5.782569456249008, 0.02, 230.1553795363387, 94.7995841359224));
        right.setSegment(14, new Trajectory.Segment(4.9772123389839775, 35.77040227866256, 
               154.02837821001006, 224.7832638952474, 5.782569456249008, 0.02, 217.99784309452534, 72.57790085886211));


        left.setSegment(15, new Trajectory.Segment(4.998627832697916, 30.15898039778021, 
               79.58435907432654, -233.68105074214753, 5.789506260106876, 0.02, 230.6855189360492, 94.51187272438634));
        right.setSegment(15, new Trajectory.Segment(5.756100835923684, 38.94442484698533, 
               158.70112841613846, 233.63751030641993, 5.789506260106876, 0.02, 218.68242122086136, 72.20639032118214));


        left.setSegment(16, new Trajectory.Segment(5.631712221288807, 31.654219429544543, 
               74.76195158821675, -241.1203743054898, 5.797844738772516, 0.02, 231.24422883166145, 94.21414953815045));
        right.setSegment(16, new Trajectory.Segment(6.60039827654686, 42.2148720311588, 
               163.5223592086735, 241.06153962675165, 5.797844738772516, 0.02, 219.4275400368953, 71.80935616870275));


        left.setSegment(17, new Trajectory.Segment(6.292727682391467, 33.05077305513299, 
               69.82768127942246, -246.71351543971412, 5.807740712935735, 0.02, 231.83039491758828, 93.90861597284874));
        right.setSegment(17, new Trajectory.Segment(7.512077739424578, 45.58397314388591, 
               168.45505563635575, 246.63482138411297, 5.807740712935735, 0.02, 220.2359983615946, 71.38798391017426));


        left.setSegment(18, new Trajectory.Segment(6.979676438214648, 34.347437791159074, 
               64.83323680130404, -249.72222390592123, 5.819351899083843, 0.02, 232.44293334130606, 93.59765733798494));
        right.setSegment(18, new Trajectory.Segment(8.493136186868194, 49.0529223721808, 
               173.44746141474445, 249.62028891943504, 5.819351899083843, 0.02, 221.11080372813763, 70.94392169835254));


        left.setSegment(19, new Trajectory.Segment(7.690566514893351, 35.54450383393512, 
               59.85330213880218, -248.99673312509307, 5.832835550232698, 0.02, 233.08080375136402, 93.28383248063176));
        right.setSegment(19, new Trajectory.Segment(9.545564558016594, 52.62141855742003, 
               178.42480926196131, 248.8673923608431, 5.832835550232698, 0.02, 222.05515007623345, 70.47936228209218));


        left.setSegment(20, new Trajectory.Segment(8.423454881766519, 36.644418343658444, 
               54.995725486166336, -242.8788326317921, 5.8483450494544424, 0.02, 233.7430301222045, 92.96985542404724));
        right.setSegment(20, new Trajectory.Segment(10.671304600286238, 56.28700211348225, 
               183.27917780311117, 242.71842705749265, 5.8483450494544424, 0.02, 223.0723742344617, 69.99713240466227));


        left.setSegment(21, new Trajectory.Segment(9.176508572483458, 37.652684535846994, 
               50.413309609427515, -229.12079383694106, 5.866025084025528, 0.02, 234.42873397082693, 92.6585676262029));
        right.setSegment(21, new Trajectory.Segment(11.872187730853469, 60.044156528361505, 
               187.85772074396263, 228.92714704257315, 5.866025084025528, 0.02, 224.16588315353218, 69.50078722518101));


        left.setSegment(22, new Trajectory.Segment(9.948088805016065, 38.57901162663037, 
               46.316354539168714, -204.84775351294005, 5.886005003915652, 0.02, 235.1371854291949, 92.35290018212129));
        right.setSegment(22, new Trajectory.Segment(13.149850912498007, 63.88315908222689, 
               191.95012769326922, 204.62034746532964, 5.886005003915652, 0.02, 225.33904280813366, 68.99470459304928));


        left.setSegment(23, new Trajectory.Segment(10.736862708455446, 39.438695171969044, 
               42.9841772669338, -166.60886361174576, 5.908390001546149, 0.02, 235.86787798875858, 92.05582669938468));
        right.setSegment(23, new Trajectory.Segment(14.505624963067278, 67.78870252846362, 
               195.27717231183635, 166.35223092835645, 5.908390001546149, 0.02, 226.5950196180385, 68.48417004066494));


        left.setSegment(24, new Trajectory.Segment(11.541945577768344, 40.25414346564486, 
               40.772414683790714, -110.58812915715421, 5.933249881613524, 0.02, 236.6206319629981, 91.77030983345955));
        right.setSegment(24, new Trajectory.Segment(15.940392374551873, 71.7383705742297, 
               197.48340228830443, 110.31149882340401, 5.933249881613524, 0.02, 227.9365670224112, 67.9754381988474));


        left.setSegment(25, new Trajectory.Segment(12.363073021330262, 41.05637217809595, 
               40.11143562255448, -33.04895306181166, 5.960605471675912, 0.02, 237.3957290478724, 91.49924750190117));
        right.setSegment(25, new Trajectory.Segment(17.45441530424503, 75.70114648465767, 
               198.13879552139824, 32.769661654690196, 5.960605471675912, 0.02, 229.3657547435484, 67.47575040693398));


        left.setSegment(26, new Trajectory.Segment(13.200796346477198, 41.88616625734679, 
               41.489703962541924, 68.91341699937215, 5.990413199148914, 0.02, 238.19407489801569, 91.24542813098113));
        right.setSegment(26, new Trajectory.Segment(19.0471404103019, 79.63625530284357, 
               196.75544090929478, -69.167730605173, 5.990413199148914, 0.02, 230.88364746674353, 66.99328284912814));


        left.setSegment(27, new Trajectory.Segment(14.05668596328349, 42.7944808403146, 
               45.41572914839058, 196.30125929243292, 6.0225490358570255, 0.02, 239.01737790093236, 91.01150677337304));
        right.setSegment(27, new Trajectory.Segment(20.716995741886812, 83.49276657924563, 
               192.82556382010299, -196.49385445958956, 6.0225490358570255, 0.02, 232.48995377249443, 66.53699688558396));


        left.setSegment(28, new Trajectory.Segment(14.933517419354315, 43.841572803541204, 
               52.3545981613303, 346.9434506469859, 6.056793814866217, 0.02, 239.8683209629386, 90.80001372956615));
        right.setSegment(28, new Trajectory.Segment(22.461205064624426, 87.21046613688063, 
               185.88497788175005, -347.02929691764695, 6.056793814866217, 0.02, 234.18268421159522, 66.11636661793021));


        left.setSegment(29, new Trajectory.Segment(15.835405363113917, 45.0943971879801, 
               62.641219221944766, 514.3310530307232, 6.092822659034598, 0.02, 240.7506916633354, 90.61340234143509));
        right.setSegment(29, new Trajectory.Segment(24.275654271622834, 90.72246034992031, 
               175.59971065198425, -514.2633614882897, 6.092822659034598, 0.02, 235.95787559772506, 65.7409715361996));


        left.setSegment(30, new Trajectory.Segment(16.76784638152408, 46.62205092050823, 
               76.38268662640648, 687.0733702230858, 6.130201604452277, 0.02, 241.66942945420544, 90.45413174012705));
        right.setSegment(30, new Trajectory.Segment(26.154848858844748, 93.95972936109574, 
               161.8634505587714, -686.8130046606424, 6.130201604452277, 0.02, 237.80944997894898, 65.41996591718424));


        left.setSegment(31, new Trajectory.Segment(17.737636908785344, 48.48952636306323, 
               93.37377212774989, 849.5542750671702, 6.168394086766876, 0.02, 242.63055270022255, 90.32476492548274));
        right.setSegment(31, new Trajectory.Segment(28.091996164828164, 96.85736529917072, 
               144.8817969037492, -849.0826827511099, 6.168394086766876, 0.02, 239.72927260252885, 65.16146876232624));


        left.setSegment(32, new Trajectory.Segment(18.7526501242886, 50.75066077516285, 
               113.05672060498111, 984.147423861561, 6.206778559442338, 0.02, 243.64094747132305, 90.22804763551325));
        right.setSegment(32, new Trajectory.Segment(30.079228378834102, 99.36161070029694, 
               125.21227005631062, -983.4763423719295, 6.206778559442338, 0.02, 241.70744712624213, 64.97194982893252));


        left.setSegment(33, new Trajectory.Segment(19.821484141824257, 53.44170087678292, 
               134.5520050810034, 1074.7642238011138, 6.2446762708468375, 0.02, 244.7080324606434, 90.16692658669555));
        right.setSegment(33, new Trajectory.Segment(32.10795396607115, 101.43627936185244, 
               103.73343307777532, -1073.9418489267648, 6.2446762708468375, 0.02, 243.73283963840433, 64.85570577537447));


        left.setSegment(34, new Trajectory.Segment(20.95302514105418, 56.57704996149623, 
               156.76745423566558, 1110.7724577331098, 6.281385752493297, 0.02, 245.8393506622705, 90.1444730879595));
        right.setSegment(34, new Trajectory.Segment(34.169293901205556, 103.0669967567203, 
               81.53586974339291, -1109.8781667191204, 6.281385752493297, 0.02, 245.79376796666926, 64.81451410224732));


        left.setSegment(35, new Trajectory.Segment(22.155991975590865, 60.14834172683416, 
               178.5645882668966, 1089.8567015615513, 0.03303345713982667, 0.02, 247.04216383664175, 90.1636999585884));
        right.setSegment(35, new Trajectory.Segment(36.25453628538565, 104.26211920900442, 
               59.75612261420622, -1088.9873564593345, 0.03303345713982667, 0.02, 247.87874913834605, 64.84751886757523));


        left.setSegment(36, new Trajectory.Segment(23.438533610962377, 64.12708176857556, 
               198.93700208707017, 1018.6206910086781, 0.06534675665490347, 0.02, 248.32312814586962, 90.22728836119295));
        right.setSegment(36, new Trajectory.Segment(38.35553814623734, 105.05009304258486, 
               39.39869167902188, -1017.8715467592169, 0.06534675665490347, 0.02, 249.97718371424602, 64.95135118417174));


        left.setSegment(37, new Trajectory.Segment(24.801562210152476, 68.15142995950488, 
               201.21740954646583, 114.02037296978307, 0.09444137849732584, 0.02, 249.6817613376835, 90.33666300318893));
        right.setSegment(37, new Trajectory.Segment(40.4555074781385, 104.99846659505798, 
               -2.581322376344275, -2099.0007027683077, 0.09444137849732584, 0.02, 252.0704069657093, 65.11954040635578));


        left.setSegment(38, new Trajectory.Segment(26.237340172666737, 71.78889812571315, 
               181.87340831041325, -967.2000618026289, 0.11967413172582095, 0.02, 251.10925128335978, 90.49071108865164));
        right.setSegment(38, new Trajectory.Segment(42.53041408116176, 103.745330151163, 
               -62.656822194748685, -3003.7749909202203, 0.11967413172582095, 0.02, 254.13336644901742, 65.34188154402193));


        left.setSegment(39, new Trajectory.Segment(27.733194823563533, 74.79273254483981, 
               150.19172095633309, -1584.0843677040084, 0.14062584838136094, 0.02, 252.59237126194273, 90.68548343201098));
        right.setSegment(39, new Trajectory.Segment(44.55696597202708, 101.32759454326599, 
               -120.88678039485075, -2911.4979100051028, 0.14062584838136094, 0.02, 256.14269529886076, 65.6055293006159));


        left.setSegment(40, new Trajectory.Segment(29.27320677985204, 77.00059781442533, 
               110.39326347927627, -1989.9228738528407, 0.15713358720553097, 0.02, 254.11525536715305, 90.91452671964305));
        right.setSegment(40, new Trajectory.Segment(46.51511417838842, 97.90741031806716, 
               -171.0092112599412, -2506.1215432545223, 0.15713358720553097, 0.02, 258.0790902030458, 65.89659488278326));


        left.setSegment(41, new Trajectory.Segment(30.84050204806279, 78.36476341053745, 
               68.2082798056058, -2109.249183683524, 0.16927520888118108, 0.02, 255.66163025391168, 91.16975047903478));
        right.setSegment(41, new Trajectory.Segment(48.389954817190954, 93.74203194012675, 
               -208.26891889702068, -1862.9853818539743, 0.16927520888118108, 0.02, 259.92892372139227, 66.20178887896824));


        left.setSegment(42, new Trajectory.Segment(32.417993991691176, 78.87459718141932, 
               25.49168854409345, -2135.8295630756174, 0.1773108471592647, 0.02, 257.2154055970128, 91.44226289229958));
        right.setSegment(42, new Trajectory.Segment(50.17098892105653, 89.05170519327861, 
               -234.51633734240716, -1312.3709222693235, 0.1773108471592647, 0.02, 261.6831926412676, 66.5093974547453));


        left.setSegment(43, new Trajectory.Segment(33.99480533909243, 78.84056737006276, 
               -1.7014905678280456, -1359.6589555960747, 0.18163752319306323, 0.02, 258.7668079968521, 91.72419305930251));
        right.setSegment(43, new Trajectory.Segment(51.857394882597795, 84.3202980770635, 
               -236.57035581075547, -102.70092341741588, 0.18163752319306323, 0.02, 263.3424293171712, 66.81089160138566));


        left.setSegment(44, new Trajectory.Segment(35.56547188295058, 78.53332719290748, 
               -15.362008857763954, -683.0259144967954, 0.18268653202455049, 0.02, 260.3114141597159, 92.00912321502209));
        right.setSegment(44, new Trajectory.Segment(53.454632818088726, 79.86189677454664, 
               -222.92006512584308, 682.5145342456195, 0.18268653202455049, 0.02, 264.91316723094144, 67.10063533093997));


        left.setSegment(45, new Trajectory.Segment(37.124745137021016, 77.96366270352156, 
               -28.48322446929572, -656.0607805765883, 0.18087217976759323, 0.02, 261.8449332203729, 92.29135073221534));
        right.setSegment(45, new Trajectory.Segment(54.967948536973395, 75.66578594423352, 
               -209.80554151565585, 655.7261805093617, 0.18087217976759323, 0.02, 266.4014859709896, 67.3745546494352));


        left.setSegment(46, new Trajectory.Segment(38.6675959493812, 77.14254061800925, 
               -41.05610427561572, -628.6439903160001, 0.1765914920819003, 0.02, 263.36315313951684, 92.56593570324456));
        right.setSegment(46, new Trajectory.Segment(56.40236961523274, 71.72105391296728, 
               -197.23660156331206, 628.4469976171891, 0.1765914920819003, 0.02, 267.81300344599606, 67.62986279169878));


        left.setSegment(47, new Trajectory.Segment(40.189204742466984, 76.0804396542893, 
               -53.105048185997816, -602.4471955191046, 0.17022497824767524, 0.02, 264.86189773274026, 92.82872452241224));
        right.setSegment(47, new Trajectory.Segment(57.76271488775252, 68.01726362598912, 
               -185.1895143489081, 602.3543607201987, 0.17022497824767524, 0.02, 269.152903077046, 67.86482712767159));


        left.setSegment(48, new Trajectory.Segment(41.68495252038, 74.787388895651, 
               -64.65253793191437, -577.3744872958275, 0.16213733473961656, 0.02, 266.3370045984966, 93.07635559826952));
        right.setSegment(48, new Trajectory.Segment(59.05360321630508, 64.54441642762765, 
               -173.6423599180732, 577.3577215417447, 0.16213733473961656, 0.02, 270.4259727046141, 68.07857089987766));


        left.setSegment(49, new Trajectory.Segment(43.150420422997755, 73.2733951308877, 
               -75.69968823816495, -552.3575153125292, 0.15267747423768055, 0.02, 267.78432789183285, 93.30625004833132));
        right.setSegment(49, new Trajectory.Segment(60.279453747858135, 61.29252657765274, 
               -162.59449249874578, 552.3933709663709, 0.15267747423768055, 0.02, 271.63664101143354, 68.27090336352282));


        left.setSegment(50, new Trajectory.Segment(44.581400941071635, 71.54902590369389, 
               -86.21846135969093, -525.938656076299, 0.1421776342398227, 0.02, 269.1997653492493, 93.51658798416196));
        right.setSegment(50, new Trajectory.Segment(61.444474541999455, 58.251039707065964, 
               -152.07434352933865, 526.0074484703565, 0.1421776342398227, 0.02, 272.78900381404054, 68.44217338994136));


        left.setSegment(51, new Trajectory.Segment(45.97392053114678, 69.62597950375742, 
               -96.15231999682337, -496.69293185662156, 0.13095158607172022, 0.02, 270.5793056946331, 93.70626993955403));
        right.setSegment(51, new Trajectory.Segment(62.55263982613614, 55.40826420683421, 
               -142.13877501158763, 496.7784258875511, 0.13095158607172022, 0.02, 273.8868373227154, 68.59314320214366));


        left.setSegment(52, new Trajectory.Segment(47.32427103880513, 67.51752538291737, 
               -105.42270604200255, -463.519302258959, 0.11929214902201185, 0.02, 271.9190903280289, 93.87486302852739));
        right.setSegment(52, new Trajectory.Segment(63.60765846676716, 52.75093203155131, 
               -132.86660876414516, 463.6083123721235, 0.11929214902201185, 0.02, 274.9335988553876, 68.72488015898088));


        left.setSegment(53, new Trajectory.Segment(48.62904594050238, 65.23874508486259, 
               -113.93901490273919, -425.81544303683216, 0.10746832493790609, 0.02, 273.2154819880471, 94.02253228035185));
        right.setSegment(53, new Trajectory.Segment(64.61293764931162, 50.26395912722257, 
               -124.34864521643689, 425.89817738541313, 0.10746832493790609, 0.02, 275.9324177550906, 68.83866522150326));


        left.setSegment(54, new Trajectory.Segment(49.8851768586555, 62.80654590765595, 
               -121.6099588603317, -383.5471978796256, 0.09572241219925703, 0.02, 274.4651328732858, 94.14995892027554));
        right.setSegment(54, new Trajectory.Segment(65.57154630824374, 47.93043294660594, 
               -116.6763090308315, 383.6168092802694, 0.09572241219925703, 0.02, 276.8860805164485, 68.93591694940017));


        left.setSegment(55, new Trajectory.Segment(51.08996599757709, 60.23945694607939, 
               -128.35444807882794, -337.22446092481204, 0.0842674427830008, 0.02, 275.66504544555903, 94.25824875435113));
        right.setSegment(55, new Trajectory.Segment(66.4861826582403, 45.73181749982764, 
               -109.9307723389149, 337.2768345958299, 0.0842674427830008, 0.02, 277.79701449330435, 69.01812973760606));


        left.setSegment(56, new Trajectory.Segment(52.24111092115029, 57.55724617865977, 
               -134.1105383709813, -287.8045146076673, 0.07328522514614305, 0.02, 276.81262061987803, 94.34883492523306));
        right.setSegment(56, new Trajectory.Segment(67.35914940702892, 43.64833743943115, 
               -104.17400301982447, 287.8384659545219, 0.07328522514614305, 0.02, 278.66727419321074, 69.08682470983405));


        left.setSegment(57, new Trajectory.Segment(53.33671922115655, 54.780415000313056, 
               -138.84155891733556, -236.55102731771365, 0.06292518618232154, 0.02, 277.9056899655753, 94.42337989514792));
        right.setSegment(57, new Trajectory.Segment(68.19233909367593, 41.65948433234994, 
               -99.4426553540606, 236.56738328819316, 0.06292518618232154, 0.02, 279.49853328094565, 69.14351141897345));


        left.setSegment(58, new Trajectory.Segment(54.375311884415744, 51.92963316295963, 
               -142.53909186767117, -184.87664751678068, 0.0533041061184456, 0.02, 278.94253056928426, 94.48368149277367));
        right.setSegment(58, new Trajectory.Segment(68.98723073891668, 39.74458226203766, 
               -95.745103515614, 184.87759192233, 0.0533041061184456, 0.02, 280.29208427795527, 69.18965838873955));


        left.setSegment(59, new Trajectory.Segment(55.35581534144574, 49.02517285149972, 
               -145.2230155729957, -134.19618526622656, 0.0445067476531154, 0.02, 279.9218630279674, 94.53158728174976));
        right.setSegment(59, new Trajectory.Segment(69.74489782471849, 37.883354290090885, 
               -93.06139859733875, 134.18524591376269, 0.0445067476531154, 0.02, 281.04884679560945, 69.22667061349198));


        left.setSegment(60, new Trajectory.Segment(56.277543107875346, 46.0863883214802, 
               -146.9392265009759, -85.81054639900998, 0.0365873068786308, 0.02, 280.84283441980597, 94.56892052192819));
        right.setSegment(60, new Trajectory.Segment(70.46602668067727, 36.05644279793917, 
               -91.34557460758579, 85.79119948764813, 0.0365873068786308, 0.02, 281.76938415271684, 69.25587239270357));


        left.setSegment(61, new Trajectory.Segment(57.14016856217146, 43.13127271480588, 
               -147.75578033371593, -40.82769163700135, 0.029571564128383167, 0.02, 281.70498896830946, 94.59741980076669));
        right.setSegment(61, new Trajectory.Segment(71.15094373570696, 34.24585275148452, 
               -90.52950232273247, 40.80361424266599, 0.029571564128383167, 0.02, 282.4539275215704, 69.27849425003606));


        left.setSegment(62, new Trajectory.Segment(57.94369071233406, 40.17610750812987, 
               -147.7582603338007, -0.12400000423866686, 0.023459588162031107, 0.02, 282.5082294604349, 94.61869320228138));
        right.setSegment(62, new Trajectory.Segment(71.79964977574836, 32.43530200207048, 
               -90.52753747070206, 0.09824260152058173, 0.023459588162031107, 0.02, 283.1024063239813, 69.2956630941994));


        left.setSegment(63, new Trajectory.Segment(58.688394862512595, 37.23520750892668, 
               -147.04499996015946, 35.663018682062386, 0.01822884278579897, 0.02, 283.2527724206158, 94.6341868054182));
        right.setSegment(63, new Trajectory.Segment(72.4118592967091, 30.6104760480369, 
               -91.2412977016789, -35.68801154884227, 0.01822884278579897, 0.02, 283.7144834370086, 69.30839515071683));


        left.setSegment(64, new Trajectory.Segment(59.374809962379096, 34.320754993324904, 
               -145.72262578008866, 66.11870900353978, 0.013837555083307508, 0.02, 283.93909970163895, 94.64516645241338));
        right.setSegment(64, new Trajectory.Segment(72.98704316853333, 28.759193591211805, 
               -92.56412284125481, -66.14125697879558, 0.013837555083307508, 0.02, 284.28959378633556, 69.31759148171014));


        left.setSegment(65, new Trajectory.Segment(60.0036641949777, 31.44271162993027, 
               -143.90216816973177, 91.02288051784484, 0.010228222462219543, 0.02, 284.56790867391663, 94.65271113532697));
        right.setSegment(65, new Trajectory.Segment(73.52447305553112, 26.871494349889314, 
               -94.38496206612452, -91.04196124348505, 0.010228222462219543, 0.02, 284.82698503155103, 69.32403609218832));


        left.setSegment(66, new Trajectory.Segment(60.57584009658425, 28.60879508032747, 
               -141.69582748013985, 110.31703447959558, 0.007331156393668471, 0.02, 285.1400626860892, 94.6577160063635));
        right.setSegment(66, new Trajectory.Segment(74.02326629932821, 24.93966218985463, 
               -96.5916080017342, -110.33229678048428, 0.007331156393668471, 0.02, 285.3257592141273, 69.32839669455657));


        left.setSegment(67, new Trajectory.Segment(61.092330254762025, 25.824507908888698, 
               -139.21435857193867, 124.07344541005898, 0.005067980326728817, 0.02, 285.65654301221156, 94.66090288933198));
        right.setSegment(67, new Trajectory.Segment(74.48243021999231, 22.958196033204754, 
               -99.07330783249381, -124.08499153798047, 0.005067980326728817, 0.02, 285.7849144043617, 69.33122818187319));


        left.setSegment(68, new Trajectory.Segment(61.5541944206197, 23.093208292883645, 
               -136.56498080025264, 132.46888858430168, 0.003355015841572058, 0.02, 286.1184031317086, 94.66283620813418));
        right.setSegment(68, new Trajectory.Segment(74.9009050007615, 20.92373903845957, 
               -101.72284973725922, -132.4770952382707, 0.003355015841572058, 0.02, 286.2033855235466, 69.33297876690334));


        left.setSegment(69, new Trajectory.Segment(61.962518706326634, 20.41621428534664, 
               -133.84970037685022, 135.76402117012094, 0.0021065040741034616, 0.02, 286.52672591900694, 94.66394240476536));
        right.setSegment(69, new Trajectory.Segment(75.27760448546044, 18.83497423494761, 
               -104.43824017559801, -135.76952191693934, 0.0021065040741034616, 0.02, 286.58008362774274, 69.33399860390158));


        left.setSegment(70, new Trajectory.Segment(62.31837741703671, 17.792935535503734, 
               -131.16393749214535, 134.28814423524358, 0.0012376191435591252, 0.02, 286.8825841426963, 94.66453114993513));
        right.setSegment(70, new Trajectory.Segment(75.61145434157584, 16.69249280576959, 
               -107.12407145890097, -134.291564165148, 0.0012376191435591252, 0.02, 286.91393302759985, 69.33455054892764));


        left.setSegment(71, new Trajectory.Segment(62.622797973409384, 15.22102781863373, 
               -128.59538584350014, 128.42758243226058, 0.0006672375647753456, 0.02, 287.18700456494037, 94.66481691732861));
        right.setSegment(71, new Trajectory.Segment(75.90142713275517, 14.498639558966813, 
               -109.69266234013881, -128.42954406189193, 0.0006672375647753456, 0.02, 287.20390569120207, 69.33482255586199));


        left.setSegment(72, new Trajectory.Segment(62.87672930960531, 12.696566809796309, 
               -126.2230504418711, 118.61677008145222, 0.00032043329279625443, 0.02, 287.4409358714186, 94.66493976885414));
        right.setSegment(72, new Trajectory.Segment(76.14657391678631, 12.257339201556498, 
               -112.06501787051577, -118.61777651884822, 0.00032043329279625443, 0.02, 287.4490524465862, 69.3349410692646));


        left.setSegment(73, new Trajectory.Segment(63.081014070141116, 10.214238026790175, 
               -124.11643915030668, 105.33056457822099, 0.00013067285590188243, 0.02, 287.6452206270656, 94.66498446133765));
        right.setSegment(73, new Trajectory.Segment(76.34605204546298, 9.973906433833339, 
               -114.17163838615795, -105.33102578210887, 0.00013067285590188243, 0.02, 287.64853057049623, 69.33498467759755));


        left.setSegment(74, new Trajectory.Segment(63.23636487370958, 7.767540178423225, 
               -122.33489241834752, 89.07733659795767, 0.00004168942415274518, 0.02, 287.80057143011015, 94.66499722001112));
        right.setSegment(74, new Trajectory.Segment(76.49914889870595, 7.654842662148643, 
               -115.95318858423478, -89.07750990384145, 0.00004168942415274518, 0.02, 287.80162742322364, 69.33499724202298));


        left.setSegment(75, new Trajectory.Segment(63.34334485888465, 5.348999258753138, 
               -120.92704598350434, 70.39232174215897, 0.000009018822006899574, 0.02, 287.90755141525597, 94.6649997216717));
        right.setSegment(75, new Trajectory.Segment(76.60530133752869, 5.3076219411364, 
               -117.36103605061214, -70.3923733188681, 0.000009018822006899574, 0.02, 287.9077798620174, 69.33499972270187));


        left.setSegment(76, new Trajectory.Segment(63.40445080351144, 3.05529723133957, 
               -114.6851013706784, 312.0972306412973, 0.000001040596859631926, 0.02, 287.9686573598822, 94.66499998912299));
        right.setSegment(76, new Trajectory.Segment(76.66620519371251, 3.045192809191384, 
               -113.12145659725081, 211.9789726680665, 0.000001040596859631926, 0.02, 287.9686837182006, 69.3349999891367));


        left.setSegment(77, new Trajectory.Segment(63.42994635300833, 1.2747774748448828, 
               -89.02598782473436, 1282.9556772972019, 3.6273206591164175e-8, 0.02, 287.99415290937907, 94.66499999992931));
        right.setSegment(77, new Trajectory.Segment(76.69167530369127, 1.2735054989378802, 
               -88.58436551267518, 1226.8545542287818, 3.6273206591164175e-8, 0.02, 287.9941538281794, 69.33499999992932));


        left.setSegment(78, new Trajectory.Segment(63.43579344362921, 0.29235453104377035, 
               -49.12114719005562, 1995.2420317339368, 6.283185307179586, 0.02, 287.99999999999994, 94.66500000000005));
        right.setSegment(78, new Trajectory.Segment(76.69752147551183, 0.2923085910282453, 
               -49.05984539548175, 1976.2260058596712, 6.283185307179586, 0.02, 287.99999999999994, 69.33500000000006));


        left.setSegment(79, new Trajectory.Segment(63.43579344362921, 0, 
               -14.617726552188516, 1725.1710318933553, 6.283185307179586, 0.02, 287.99999999999994, 94.66500000000005));
        right.setSegment(79, new Trajectory.Segment(76.69752147551183, 0, 
               -14.615429551412264, 1722.2207922034745, 6.283185307179586, 0.02, 287.99999999999994, 69.33500000000006));


    }


    public boolean isReversed() {
        return false; 
    }
  
	// WAYPOINT_DATA: [{"position":{"x":220,"y":86},"theta":-0.5235833333312303,"comment":""},{"position":{"x":288,"y":82},"theta":0,"comment":""}]
	// IS_REVERSED: false
	// FILE_NAME: SecondCubeRight
  // DT: 0.02
  // MAX_VEL: 140
  // MAX_ACC: 120
  // MAX_JERK: 2000
  // WHEEL_BASE: 25.33
  // PACKAGE: org.usfirst.frc862.glitch.paths
  // PARENT: org.usfirst.frc862.util.DynamicPathCommand
}