package org.usfirst.frc862.glitch.paths;
 
import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory;

// max path velocity 104.09734332106149
public class RightSwitch20Degree extends org.usfirst.frc862.util.DynamicPathCommand {
    private static Path path;

    public Path getPath() {
        return RightSwitch20Degree.generatePath();
    }

    private static Path generatePath() {
        if (path != null) {
            return path;
        }

        int num_elements = 82;
        Trajectory left = new Trajectory(num_elements);
        Trajectory right = new Trajectory(num_elements);

        build_segments_0(left, right);

       
        path = new Path("RightSwitch20Degree", new Trajectory.Pair(left, right));
        return path;
    }


    private static void build_segments_0(Trajectory left, Trajectory right) {

        left.setSegment(0, new Trajectory.Segment(0.007912975976867272, 0.7912975976867272, 
               39.564879884336364, 1978.2439942168182, 2.618009370463726, 0.02, 278.6608170260973, 70.03564653835613));
        right.setSegment(0, new Trajectory.Segment(0.007912975976867272, 0.7912975976867272, 
               39.564879884336364, 1978.2439942168182, 2.618009370463726, 0.02, 291.32547717514575, 91.97226622574422));


        left.setSegment(1, new Trajectory.Segment(0.039549629983200385, 1.5818327003166557, 
               39.52675513149642, -1.9062376419970661, 2.6180105745618723, 0.02, 278.6334186275681, 70.05146442806222));
        right.setSegment(1, new Trajectory.Segment(0.039580129789174544, 1.5833576906153637, 
               39.60300464643182, 1.9062381047728394, 2.6180105745618723, 0.02, 291.2980523627644, 91.98809936492822));


        left.setSegment(2, new Trajectory.Segment(0.11065793913076426, 3.5554154573781935, 
               98.6791378530769, 2957.6191360790235, 2.618019139476947, 0.02, 278.5718363035728, 70.08701733231999));
        right.setSegment(2, new Trajectory.Segment(0.11090538823566232, 3.566262922324389, 
               99.14526158545127, 2977.112846950972, 2.618019139476947, 0.02, 291.2362821528892, 92.02376073989376));


        left.setSegment(3, new Trajectory.Segment(0.22894463879125213, 5.914334983024394, 
               117.94597628231, 963.3419214616551, 2.61805134950414, 0.02, 278.4693946808406, 70.14615663607444));
        right.setSegment(3, new Trajectory.Segment(0.23000796788491895, 5.955128982462832, 
               119.44330300692216, 1014.9020710735449, 2.61805134950414, 0.02, 291.13313394048583, 92.08330795441381));


        left.setSegment(4, new Trajectory.Segment(0.3940724832033387, 8.25639222060433, 
               117.10286187899679, -42.155720165660426, 2.6181338328100083, 0.02, 278.3263819239859, 70.22870696240327));
        right.setSegment(4, new Trajectory.Segment(0.3972251144339705, 8.360857327452578, 
               120.2864172494873, 42.155712128256795, 2.6181338328100083, 0.02, 290.9883116917923, 92.16690275319556));


        left.setSegment(5, new Trajectory.Segment(0.6055761369892786, 10.575182689296994, 
               115.93952343463324, -58.16692221817732, 2.6183033312174335, 0.02, 278.14319126025515, 70.33441872280247));
        right.setSegment(5, new Trajectory.Segment(0.6130221628748077, 10.78985242204186, 
               121.44975472946413, 58.166873998841595, 2.6183033312174335, 0.02, 290.80140235694427, 92.27476037537608));


        left.setSegment(6, new Trajectory.Segment(0.8628686537581831, 12.864625838445223, 
               114.47215745741141, -73.36829886109157, 2.6186061886601424, 0.02, 277.92031127398656, 70.46296424355752));
        right.setSegment(6, new Trajectory.Segment(0.877986058637931, 13.248194788156164, 
               122.91711830571516, 73.36817881255158, 2.6186061886601424, 0.02, 290.57187699448997, 92.40713852330161));


        left.setSegment(7, new Trajectory.Segment(1.165249362886576, 15.119035456419654, 
               112.72048089872158, -87.58382793449186, 2.619097728408843, 0.02, 277.6583141046097, 70.61393303666227));
        right.setSegment(7, new Trajectory.Segment(1.1928174694746878, 15.741570541837845, 
               124.66878768408405, 87.5834689184444, 2.619097728408843, 0.02, 290.2990918632558, 92.5643234126104));


        left.setSegment(8, new Trajectory.Segment(1.5119128829616515, 17.333176003753774, 
               110.70702736670599, -100.67267660077945, 2.6198415413513993, 0.02, 277.3578419959261, 70.7868263796633));
        right.setSegment(8, new Trajectory.Segment(1.558321770948037, 18.27521507366746, 
               126.68222659148078, 100.67194536983664, 2.6198415413513993, 0.02, 289.98228927481836, 92.74661305673442));


        left.setSegment(9, new Trajectory.Segment(1.9019589679922682, 19.502304251530834, 
               108.456412388853, -112.53074889264951, 2.6209087084504183, 0.02, 277.01959268456045, 70.98105138270971));
        right.setSegment(9, new Trajectory.Segment(1.975399197308883, 20.853871318042298, 
               128.93281221874187, 112.52928136305442, 2.6209087084504183, 0.02, 289.62059801741003, 92.95429794760078));


        left.setSegment(10, new Trajectory.Segment(2.3344029107865447, 21.622197139713833, 
               105.99464440914996, -123.08839898515203, 2.6223769792016185, 0.02, 276.6443041216915, 71.19591473053623));
        right.setSegment(10, new Trajectory.Segment(2.4450344348796262, 23.48176187853717, 
               131.39452802474364, 123.08579030008815, 2.6223769792016185, 0.02, 289.2130332081521, 93.18763929117858));


        left.setSegment(11, new Trajectory.Segment(2.8081862718729815, 23.689168054321847, 
               103.34854573040069, -132.30493393746343, 2.624329924338212, 0.02, 276.23273899906303, 71.43061629226553));
        right.setSegment(11, new Trajectory.Segment(2.968285888394586, 26.162572675747985, 
               134.0405398605407, 132.30059178985272, 2.624329924338212, 0.02, 288.75849551277304, 93.44684493750016));


        left.setSegment(12, new Trajectory.Segment(3.3221877775556905, 25.700075284135444, 
               100.54536149067985, -140.15921198604175, 2.6268560751449765, 0.02, 275.78566949371856, 71.68424279962456));
        right.setSegment(12, new Trajectory.Segment(3.5462747769632657, 28.899444428433966, 
               136.8435876342991, 140.1523886879204, 2.6268560751449765, 0.02, 288.2557697866791, 93.73204311356754));


        left.setSegment(13, new Trajectory.Segment(3.875234333724224, 27.65232780842667, 
               97.61262621456126, -146.6367638059296, 2.630048053613865, 0.02, 275.30386255970757, 71.95576180748368));
        right.setSegment(13, new Trajectory.Segment(4.180174113365079, 31.69496682009067, 
               139.77611958283518, 146.62659742680404, 2.630048053613865, 0.02, 287.70352334111976, 94.04325402606541));


        left.setSegment(14, new Trajectory.Segment(4.466112230643946, 29.543894845986056, 
               94.57835187796934, -151.71371682959602, 2.6340016874177103, 0.02, 274.78806598181313, 72.24401617310659));
        right.setSegment(14, new Trajectory.Segment(4.871197489217638, 34.551168792627955, 
               142.81009862686423, 151.69895220145264, 2.6340016874177103, 0.02, 287.100304224061, 94.38035935499025));


        left.setSegment(15, new Trajectory.Segment(5.093578771494352, 31.37332704252031, 
               91.47160982671262, -155.33710256283584, 2.638815091548145, 0.02, 274.2389952633385, 72.54771932751977));
        right.setSegment(15, new Trajectory.Segment(5.620587438848413, 37.469497481538745, 
               145.91643444553952, 155.31679093376454, 2.638815091548145, 0.02, 286.4445401213439, 94.74306962255271));


        left.setSegment(16, new Trajectory.Segment(5.756374725077038, 33.13979767913427, 
               88.32353183069799, -157.40389980073175, 2.6445876846702454, 0.02, 273.657321262713, 72.86545165681905));
        right.setSegment(16, new Trajectory.Segment(6.429602972987038, 40.45077670693121, 
               149.06396126962312, 157.37634120417994, 2.6445876846702454, 0.02, 285.73453874474956, 95.13088939991296));


        left.setSegment(17, new Trajectory.Segment(6.45323820010821, 34.843173751558616, 
               85.16880362121739, -157.73641047402975, 2.6514190933814725, 0.02, 273.0436583031608, 73.19565837223547));
        right.setSegment(17, new Trajectory.Segment(7.299505693903958, 43.495136045846024, 
               152.2179669457408, 157.70028380588457, 2.6514190933814725, 0.02, 284.96849085333577, 95.54308034100598));


        left.setSegment(18, new Trajectory.Segment(7.182920727564542, 36.48412637281662, 
               82.0476310629001, -156.0586279158649, 2.6594078821193, 0.02, 272.3985522846273, 73.5366493042659));
        right.setSegment(18, new Trajectory.Segment(8.231543701584421, 46.60190038402313, 
               155.33821690885537, 156.012498155728, 2.6594078821193, 0.02, 284.14447737699874, 95.978622116931));


        left.setSegment(19, new Trajectory.Segment(7.944206527493458, 38.06428999644578, 
               79.00818118145807, -151.97249407210123, 2.6686500316200155, 0.02, 271.7224681145667, 73.88660110689999));
        right.setSegment(19, new Trajectory.Segment(9.226932314649018, 49.76943065322983, 
               158.37651346033502, 151.91482757398234, 2.6686500316200155, 0.02, 283.2604824426702, 96.43617151000255));


        left.setSegment(20, new Trajectory.Segment(8.73593609888505, 39.586478569579604, 
               76.10942865669124, -144.93762623834172, 2.6792370762155437, 0.02, 271.0157755626038, 74.24356238101251));
        right.setSegment(20, new Trajectory.Segment(10.286830472551813, 52.99490789513981, 
               161.27386209549888, 144.86743175819328, 2.6792370762155437, 0.02, 282.3144144268828, 96.9140202461418));


        left.setSegment(21, new Trajectory.Segment(9.55703536720222, 41.05496341585847, 
               73.42424231394311, -134.25931713740624, 2.691253802277577, 0.02, 270.2787324610067, 74.60546219424701));
        right.setSegment(21, new Trajectory.Segment(11.412311579781724, 56.27405536149552, 
               163.95737331778548, 134.17556111432987, 2.691253802277577, 0.02, 281.3041374382608, 97.41005263318148));


        left.setSegment(22, new Trajectory.Segment(10.406551625427797, 42.47581291127886, 
               71.04247477101957, -119.08837714617704, 2.70477541062109, 0.02, 269.5114640392196, 74.97012236744656));
        right.setSegment(22, new Trajectory.Segment(12.604327567131653, 59.60079936749649, 
               166.33720030004858, 118.99134911315485, 2.70477541062109, 0.02, 280.22751577882525, 97.92170477725212));


        left.setSegment(23, new Trajectory.Segment(11.283697324297625, 43.85728494349138, 
               69.07360161062606, -98.4436580196757, 2.719864059295305, 0.02, 268.7139371743779, 75.33527367430662));
        right.setSegment(23, new Trajectory.Segment(13.863665110203923, 62.966877153613545, 
               168.30388930585264, 98.33445029020282, 2.719864059295305, 0.02, 279.0824738666103, 98.44592807572565));


        left.setSegment(24, new Trajectory.Segment(12.187902352358186, 45.21025140302804, 
               67.64832297683299, -71.2639316896535, 2.73656473630815, 0.02, 267.88592851868634, 75.69857575091757));
        right.setSegment(24, new Trajectory.Segment(15.1908933695728, 66.36141296844382, 
               169.7267907415135, 71.14507178304308, 2.73656473630815, 0.02, 277.8670736534182, 98.97916083375898));


        left.setSegment(25, new Trajectory.Segment(13.118874698080035, 46.54861728609239, 
               66.91829415321742, -36.50144118077847, 2.754900470875233, 0.02, 267.0269859403152, 76.05764002867299));
        right.setSegment(25, new Trajectory.Segment(16.58630336450329, 69.77049974652462, 
               170.45433890404027, 36.37740812633865, 2.754900470875233, 0.02, 276.579610585391, 99.51731313786807));


        left.setSegment(26, new Trajectory.Segment(14.076668240196433, 47.88967710581992, 
               67.05299098637667, 6.734841657962676, 2.7748669823439442, 0.02, 266.1363835836288, 76.41005443187532));
        right.setSegment(26, new Trajectory.Segment(18.049840239890596, 73.17684376936519, 
               170.31720114202855, -6.856888100585934, 2.7748669823439442, 0.02, 275.218727449638, 100.05577137807015));


        left.setSegment(27, new Trajectory.Segment(15.061754842697672, 49.25433012506192, 
               68.23265096209994, 58.98299878616342, 2.7964269896511125, 0.02, 265.21307219162173, 76.75340802195035));
        right.setSegment(27, new Trajectory.Segment(19.58103124922744, 76.55955046684215, 
               169.1353348738481, -59.09331340902213, 2.7964269896511125, 0.02, 273.78354291313775, 100.58942974562808));


        left.setSegment(28, new Trajectory.Segment(16.07509601683048, 50.66705870664041, 
               70.63642907892458, 120.18890584123199, 2.8195045551802975, 0.02, 264.2556281182378, 77.08531340234482));
        right.setSegment(28, new Trajectory.Segment(21.17891418584974, 79.89414683111515, 
               166.72981821364985, -120.27583300991296, 2.8195045551802975, 0.02, 272.273788207539, 101.11275622887835));


        left.setSegment(29, new Trajectory.Segment(17.11820738377403, 52.15556834717763, 
               74.42548202686083, 189.45264739681278, 2.8439799976130713, 0.02, 263.26220651212947, 77.40342476077772));
        right.setSegment(29, new Trajectory.Segment(22.841973034778015, 83.15294244641365, 
               162.9397807649248, -189.50187243625294, 2.8439799976130713, 0.02, 270.68994151219755, 101.61989958203863));


        left.setSegment(30, new Trajectory.Segment(18.193207416127954, 53.75000161769614, 
               79.72166352592573, 264.809074953245, 2.869686046127915, 0.02, 262.23050610429254, 77.70545015379967));
        right.setSegment(30, new Trajectory.Segment(24.568089348079234, 86.30581566506103, 
               157.6436609323693, -264.8059916277745, 2.869686046127915, 0.02, 269.03334579270484, 102.10484096942788));


        left.setSegment(31, new Trajectory.Segment(19.30284105159204, 55.481681773204315, 
               86.58400777540862, 343.11721247414437, 2.896405978509399, 0.02, 261.15775424142026, 77.98915818269082));
        right.setSegment(31, new Trajectory.Segment(26.35451873694792, 89.32146944343427, 
               150.78268891866173, -343.04860068537835, 2.896405978509399, 0.02, 267.30629311596834, 102.56158927341058));


        left.setSegment(32, new Trajectory.Segment(20.450469365076568, 57.38141567422641, 
               94.98669505110477, 420.1343637848076, 2.9238744388121622, 0.02, 260.0407205580204, 78.25238146445258));
        right.setSegment(32, new Trajectory.Segment(28.197901275986172, 92.16912695191262, 
               142.38287542391745, -419.99067473721396, 2.9238744388121622, 0.02, 265.512058019732, 102.98441264630398));


        left.setSegment(33, new Trajectory.Segment(21.640019021467104, 59.477482819526806, 
               104.80335726501977, 490.8331106957497, 2.9517814297258584, 0.02, 258.87576536473347, 78.4930218240199));
        right.setSegment(33, new Trajectory.Segment(30.094312073155734, 94.82053985847813, 
               132.57064532827556, -490.6115047820947, 2.9517814297258584, 0.02, 263.6548654400913, 103.36809172281042));


        left.setSegment(34, new Trajectory.Segment(22.8758897494154, 61.793536397414826, 
               115.80267889440101, 549.9660814690621, 2.97977961829101, 0.02, 257.6589243030485, 78.70906415042101));
        right.setSegment(34, new Trajectory.Segment(32.03935375144408, 97.25208391441751, 
               121.57720279696917, -549.6721265653192, 2.97977961829101, 0.02, 261.73978542668965, 103.70817358434995));


        left.setSegment(35, new Trajectory.Segment(24.162823964835404, 64.34671077100013, 
               127.65871867926535, 592.8019892432168, 3.007494628592763, 0.02, 256.3860247063178, 78.89860647145144));
        right.setSegment(35, new Trajectory.Segment(34.02828670559769, 99.44664770768033, 
               109.7281896631408, -592.4506566914189, 3.007494628592763, 0.02, 259.77255675112724, 104.00120196891902));


        left.setSegment(36, new Trajectory.Segment(25.50574869937188, 67.14623672682366, 
               139.9762977911763, 615.878955595548, 3.034537519345515, 0.02, 255.05282282716712, 79.05991236737258));
        right.setSegment(36, new Trajectory.Segment(36.05618698357583, 101.3950138989069, 
               97.41830956132844, -615.4940050906176, 3.034537519345515, 0.02, 257.7593526230819, 104.24489988898604));


        left.setSegment(37, new Trajectory.Segment(26.909604583198163, 70.1927941913142, 
               152.32787322452737, 617.5787716675529, 3.0605182819250754, 0.02, 253.65514650492227, 79.19148835602473));
        right.setSegment(37, new Trajectory.Segment(38.118117065674014, 103.09650410490947, 
               85.07451030012874, -617.1899630599853, 3.0605182819250754, 0.02, 255.70651132611178, 104.43828621972196));


        left.setSegment(38, new Trajectory.Segment(28.372582718476345, 73.14890676390912, 
               147.8056286297459, -226.11222973907275, 3.084955081909236, 0.02, 252.19561220320878, 79.2918110674302));
        right.setSegment(38, new Trajectory.Segment(40.20006393209524, 104.09734332106149, 
               50.041960807600816, -1751.627474626396, 3.084955081909236, 0.02, 253.62947501256144, 104.58119495561449));


        left.setSegment(39, new Trajectory.Segment(29.88528234218847, 75.6349811856064, 
               124.3037210848641, -1175.0953772440907, 3.107340737694768, 0.02, 250.68445763970655, 79.36016336647856));
        right.setSegment(39, new Trajectory.Segment(42.2797803651793, 103.9858216542031, 
               -5.576083342919702, -2780.902207526026, 3.107340737694768, 0.02, 251.55188903526206, 104.6753063203314));


        left.setSegment(40, new Trajectory.Segment(31.433826081403424, 77.42718696074772, 
               89.6102887570656, -1734.6716163899246, 3.1272632192462533, 0.02, 249.13636142333917, 79.39738993133248));
        right.setSegment(40, new Trajectory.Segment(44.33295220483434, 102.65859198275216, 
               -66.36148357254683, -3039.2700114813565, 3.1272632192462533, 0.02, 249.49931357397332, 104.72478943732894));


        left.setSegment(41, new Trajectory.Segment(33.0023337436672, 78.42538311318881, 
               49.909807622054814, -1985.0240567505396, 3.1444685657778852, 0.02, 247.5678773423914, 79.40599076812947));
        right.setSegment(41, new Trajectory.Segment(46.3372659092292, 100.21568521974291, 
               -122.14533815046238, -2789.1927288957772, 3.1444685657778852, 0.02, 247.49503058708478, 104.73588601762154));


        left.setSegment(42, new Trajectory.Segment(34.57591557333408, 78.67909148334401, 
               12.68541850775975, -1861.2194557147532, 3.158882814704502, 0.02, 245.9943791958225, 79.38976247795536));
        right.setSegment(42, new Trajectory.Segment(48.275957495547885, 96.93457931593396, 
               -164.05529519044748, -2095.497851999255, 3.158882814704502, 0.02, 245.55644123578287, 104.71597637469002));


        left.setSegment(43, new Trajectory.Segment(36.141129056651934, 78.26067416589288, 
               -20.920865872556504, -1680.3142190158126, 3.1705759607599453, 0.02, 244.4295923334791, 79.35322039539935));
        right.setSegment(43, new Trajectory.Segment(50.13735667546796, 93.06995899600396, 
               -193.23101599649988, -1458.7860403026198, 3.1705759607599453, 0.02, 243.695547943073, 104.67258213366031));


        left.setSegment(44, new Trajectory.Segment(37.69083662469092, 77.48537840194913, 
               -38.76478819718727, -892.1961162315383, 3.1797449495378314, 0.02, 242.88076778793175, 79.30091290504328));
        right.setSegment(44, new Trajectory.Segment(51.91931391198102, 89.0978618256528, 
               -198.6048585175581, -268.6921260529118, 3.1797449495378314, 0.02, 241.9146045622042, 104.61247999641972));


        left.setSegment(45, new Trajectory.Segment(39.22192032672415, 76.5541851016617, 
               -46.55966501437163, -389.743840859218, 3.186635881426066, 0.02, 241.35101954018015, 79.23697855486509));
        right.setSegment(45, new Trajectory.Segment(53.62494457107806, 85.28153295485201, 
               -190.81644354003942, 389.4207488759349, 3.186635881426066, 0.02, 240.21046034903944, 104.54128697717738));


        left.setSegment(46, new Trajectory.Segment(40.73125552615352, 75.4667599714683, 
               -54.37125650966976, -390.57957476490657, 3.1914954032086444, 0.02, 239.8433957699144, 79.16512236817137));
        right.setSegment(46, new Trajectory.Segment(55.25737133481384, 81.62133818678913, 
               -183.00973840314398, 390.33525684477155, 3.1914954032086444, 0.02, 238.57988369187652, 104.46358946063869));


        left.setSegment(47, new Trajectory.Segment(42.21577285200202, 74.22586629242505, 
               -62.04468395216267, -383.6713721246454, 3.194565814829968, 0.02, 238.36085221697363, 79.08859587355579));
        right.setSegment(47, new Trajectory.Segment(56.819662155810335, 78.11454104982465, 
               -175.33985684822397, 383.4940777460005, 3.194565814829968, 0.02, 237.0196695094515, 104.38306422031184));


        left.setSegment(48, new Trajectory.Segment(43.67249976744133, 72.83634577196563, 
               -69.47602602297138, -371.56710354043554, 3.1960817849458185, 0.02, 236.9062368082265, 79.01019103441436));
        right.setSegment(48, new Trajectory.Segment(58.31478859034895, 74.75632172693051, 
               -167.91096614470717, 371.44453517584, 3.1960817849458185, 0.02, 235.52670999841135, 104.30259712361436));


        left.setSegment(49, new Trajectory.Segment(45.098588800196744, 71.30445163777078, 
               -76.59470670974216, -355.93403433853865, 3.1962681194045937, 0.02, 235.48227946528053, 78.93224608707655));
        right.setSegment(49, new Trajectory.Segment(59.74559747491408, 71.54044422825663, 
               -160.79387493369381, 355.854560550668, 3.1962681194045937, 0.02, 234.0980398326419, 104.22439468381367));


        left.setSegment(50, new Trajectory.Segment(46.491337476018096, 69.63743379106769, 
               -83.35089233515447, -337.8092812706157, 3.195338184862203, 0.02, 234.09158330293437, 78.85666131322759));
        right.setSegment(50, new Trajectory.Segment(61.11479090971489, 68.45967174004066, 
               -154.03862441079852, 337.76252614476476, 3.195338184862203, 0.02, 232.73086430806725, 104.1500862259793));


        left.setSegment(51, new Trajectory.Segment(47.848203300403235, 67.8432912192569, 
               -89.70712859053975, -317.8118127692642, 3.1934927235437747, 0.02, 232.7366152318329, 78.7849229575179));
        right.setSegment(51, new Trajectory.Segment(62.4249112056649, 65.50601479750019, 
               -147.68284712702382, 317.788864188735, 3.1934927235437747, 0.02, 231.42257656487578, 104.0808159518706));


        left.setSegment(52, new Trajectory.Segment(49.166815800347784, 65.93062499722734, 
               -95.63331110147786, -296.30912554690525, 3.1909189003850953, 0.02, 231.4196952948669, 78.71813370215847));
        right.setSegment(52, new Trajectory.Segment(63.67832878311762, 62.670878872636, 
               -141.75679624320912, 296.3025441907348, 3.1909189003850953, 0.02, 230.170768063699, 104.01732500882372));


        left.setSegment(53, new Trajectory.Segment(50.44498665364604, 63.90854266491282, 
               -101.10411661572591, -273.5402757124028, 3.18778950149203, 0.02, 230.1429849523132, 78.65704825841246));
        right.setSegment(53, new Trajectory.Segment(64.8772319948967, 59.94516058895407, 
               -136.28591418409675, 273.5441029556185, 3.18778950149203, 0.02, 228.9732349686464, 103.96002407106562));


        left.setSegment(54, new Trajectory.Segment(51.68071825448989, 61.786580042192575, 
               -106.09813113601234, -249.70072601432136, 3.1842622563821683, 0.02, 228.90847505942094, 78.60211271731922));
        right.setSegment(54, new Trajectory.Segment(66.02361852349077, 57.31932642970303, 
               -131.29170796255173, 249.7103110772514, 3.1842622563821683, 0.02, 227.82798196507827, 103.90905711353344));


        left.setSegment(55, new Trajectory.Segment(52.872210641189476, 59.574619334979324, 
               -110.59803536066255, -224.99521123251043, 3.180479289875527, 0.02, 227.71797452295743, 78.55350633663531));
        right.setSegment(55, new Trajectory.Segment(67.11928842575476, 54.783495113199386, 
               -126.79156582518232, 225.00710686847043, 3.180479289875527, 0.02, 226.73322425467944, 103.86435711074259));


        left.setSegment(56, new Trajectory.Segment(54.01786649097026, 57.28279248903944, 
               -114.59134229699437, -199.66534681659098, 3.176566726911221, 0.02, 226.5731006500914, 78.51118445853841));
        right.setSegment(56, new Trajectory.Segment(68.16583911888291, 52.32753465640778, 
               -122.79802283958041, 199.67714928009528, 3.176566726911221, 0.02, 225.687387963828, 103.82569438935461));


        left.setSegment(57, new Trajectory.Segment(55.116293818805545, 54.92136639176406, 
               -118.07130486376884, -173.99812833872375, 3.172634478925971, 0.02, 225.47527207664035, 78.47492128369377));
        right.setSegment(57, new Trajectory.Segment(69.16466266943156, 49.94117752743248, 
               -119.31785644876491, 174.0083195407749, 3.172634478925971, 0.02, 224.68910891220878, 103.79271833396832));


        left.setSegment(58, new Trajectory.Segment(56.166306055952845, 52.50061185736497, 
               -121.03772671995436, -148.32109280927597, 3.1687762373645736, 0.02, 224.4257049401786, 78.44435129483924));
        right.setSegment(58, new Trajectory.Segment(70.11694570844688, 47.61415195076539, 
               -116.3512788333545, 148.32888077052075, 3.1687762373645736, 0.02, 223.73722956163584, 103.76499310449998));


        left.setSegment(59, new Trajectory.Segment(57.16691928835763, 50.030661620239414, 
               -123.49751185627795, -122.98925681617945, 3.165069691610448, 0.02, 223.42541269769052, 78.41900823895179));
        right.setSegment(59, new Trajectory.Segment(71.02367219064395, 45.33632410985327, 
               -113.89139204560585, 122.9943393874322, 3.165069691610448, 0.02, 222.8307939509393, 103.7420279748761));


        left.setSegment(60, new Trajectory.Segment(58.11734657299347, 47.52136423179191, 
               -125.46486942237536, -98.36787830487026, 3.1615769778077913, 0.02, 222.47520971714528, 78.39836074168964));
        right.setSegment(60, new Trajectory.Segment(71.88562907962843, 43.09784444922386, 
               -111.9239830314708, 98.37045070675288, 3.1615769778077913, 0.02, 221.96904047801263, 103.72330284826425));


        left.setSegment(61, new Trajectory.Segment(59.01698939112567, 44.982140906609864, 
               -126.96116625910214, -74.81484183633924, 3.158345353804122, 0.02, 221.57571853213034, 78.38184383225523));
        right.setSegment(61, new Trajectory.Segment(72.70341489737002, 40.889290887079575, 
               -110.42767810721408, 74.81524621283597, 3.158345353804122, 0.02, 221.15139248441182, 103.70828944559047));


        left.setSegment(62, new Trajectory.Segment(59.86542642847453, 42.42185186744302, 
               -128.01445195834233, -52.66428496200959, 3.155408085223099, 0.02, 220.72738045213595, 78.36888588578441));
        right.setSegment(62, new Trajectory.Segment(73.47745094831203, 38.701802547100364, 
               -109.37441699896056, 52.66305541267613, 3.155408085223099, 0.02, 220.37744670086357, 103.69646860442774));


        left.setSegment(63, new Trajectory.Segment(60.66239997636511, 39.848677394528956, 
               -128.65872364570308, -32.21358436803712, 3.1527855193851844, 0.02, 219.9304690826369, 78.3589307262951));
        right.setSegment(63, new Trajectory.Segment(74.20799492257117, 36.52719871295712, 
               -108.73019170716205, 32.21126458992529, 3.1527855193851844, 0.02, 219.64695971181342, 103.68734406856065));


        left.setSegment(64, new Trajectory.Segment(61.40780032453331, 37.270017408410055, 
               -128.932999305945, -13.713783012096314, 3.150486317789803, 0.02, 219.1851062243884, 78.35145485892846));
        right.setSegment(64, new Trajectory.Segment(74.89515650716224, 34.358079229553105, 
               -108.45597417020088, 13.710876848058717, 3.150486317789803, 0.02, 218.95983267998304, 103.68045309869686));


        left.setSegment(65, new Trajectory.Segment(62.10164856323417, 34.69241193504298, 
               -128.8802736683536, 2.63628187957039, 3.1485088141607234, 0.02, 218.49127958589332, 78.34598000158608));
        right.setSegment(65, new Trajectory.Segment(75.53891458710567, 32.18790399717109, 
               -108.50876161910072, -2.6393724449924605, 3.1485088141607234, 0.02, 218.31609463525115, 103.67537419554715));


        left.setSegment(66, new Trajectory.Segment(62.74407823398417, 32.12148353749999, 
               -128.54641987714962, 16.692689560198914, 3.146842463380513, 0.02, 217.84886174552753, 78.34208125310722));
        right.setSegment(66, new Trajectory.Segment(76.1391355974809, 30.011050518761632, 
               -108.84267392047295, -16.695615068611147, 3.146842463380513, 0.02, 217.7158846743497, 103.67173220014045));


        left.setSegment(67, new Trajectory.Segment(63.33531626782749, 29.561901692165932, 
               -127.97909226670292, 28.366380522334822, 3.1454693466264194, 0.02, 217.25762983066588, 78.3393913624147));
        right.setSegment(67, new Trajectory.Segment(76.69559258667886, 27.822849459897387, 
               -109.41005294321222, -28.368951136963716, 3.1454693466264194, 0.02, 217.15943344201006, 103.66920102354827));


        left.setSegment(68, new Trajectory.Segment(63.87566363513386, 27.017368365318745, 
               -127.22666634235935, 37.62129621717847, 3.1443656992533207, 0.02, 216.71728542726947, 78.33760164927702));
        right.setSegment(68, new Trajectory.Segment(77.2079845674458, 25.619599038347758, 
               -110.16252107748147, -37.623406713462515, 3.1443656992533207, 0.02, 216.6470442706356, 103.66750425824719));


        left.setSegment(69, new Trajectory.Segment(64.3654761039249, 24.49062343955238, 
               -126.33724628831828, 44.4710027020534, 3.1435034300812164, 0.02, 216.22747428854478, 78.33646017461523));
        right.setSegment(69, new Trajectory.Segment(77.67595575878556, 23.398559566987817, 
               -111.05197356799702, -44.472624525777604, 3.1435034300812164, 0.02, 216.17907434946898, 103.66641393386827));


        left.setSegment(70, new Trajectory.Segment(64.80514546984612, 21.98346829606078, 
               -125.3577571745799, 48.97445568691907, 3.1428516034195066, 0.02, 215.787805467832, 78.33576777113275));
        right.setSegment(70, new Trajectory.Segment(78.09911435565883, 21.15792984366356, 
               -112.03148616621287, -48.97562991079241, 3.1428516034195066, 0.02, 215.7559162770692, 103.66574769768447));


        left.setSegment(71, new Trajectory.Segment(65.19508158312964, 19.49680566417558, 
               -124.33313159426014, 51.23127901598821, 3.1423778581577544, 0.02, 215.39786955485823, 78.33537252946495));
        right.setSegment(71, new Trajectory.Segment(78.47705050157484, 18.896807295800315, 
               -113.05612739316224, -51.232061347468516, 3.1423778581577544, 0.02, 215.37798032519555, 103.66536472090756));


        left.setSegment(72, new Trajectory.Segment(65.5356954577233, 17.030693729683254, 
               -123.30559672461625, 51.37674348219434, 3.142049741395127, 0.02, 215.05725574452788, 78.33516329766263));
        right.setSegment(72, new Trajectory.Segment(78.80935317860862, 16.61513385168888, 
               -114.08367220557167, -51.377240620471554, 3.142049741395127, 0.02, 215.04567771082196, 103.66516065157359));


        left.setSegment(73, new Trajectory.Segment(65.82738370833636, 14.58441253065271, 
               -122.3140599515272, 49.576838654452615, 3.1418359372338323, 0.02, 214.7655675112622, 78.33506269922655));
        right.setSegment(73, new Trajectory.Segment(79.09562576982648, 14.313629560893359, 
               -115.07521453977611, -49.57711671022196, 3.1418359372338323, 0.02, 214.75940513661948, 103.66506194962408));


        left.setSegment(74, new Trajectory.Segment(66.07051452171102, 12.156540668733166, 
               -121.39359309597717, 46.0233427775016, 3.1417073744284068, 0.02, 214.52243670161715, 78.33502011315284));
        right.setSegment(74, new Trajectory.Segment(79.33550008734197, 11.993715875774662, 
               -115.99568425593488, -46.02348580793816, 3.1417073744284068, 0.02, 214.51953082278146, 103.66501994647041));


        left.setSegment(75, new Trajectory.Segment(66.26541533212317, 9.745040520607237, 
               -120.57500740629648, 40.92928448403441, 3.141638199481273, 0.02, 214.32753589179146, 78.33500499368313));
        right.setSegment(75, new Trajectory.Segment(79.52864869634361, 9.657430450082058, 
               -116.81427128463015, -40.92935143476382, 3.141638199481273, 0.02, 214.32638221436065, 103.66500496741048));


        left.setSegment(76, new Trajectory.Segment(66.41236233710576, 7.347350249129314, 
               -119.88451357389613, 34.52469162001748, 3.1416066044473876, 0.02, 214.18058888686753, 78.33500084174784));
        right.setSegment(76, new Trajectory.Segment(79.6747953991179, 7.30733513871419, 
               -117.50476556839344, -34.524714188164296, 3.1416066044473876, 0.02, 214.18023551164467, 103.6650008392829));


        left.setSegment(77, new Trajectory.Segment(66.51157195885182, 4.960481087302949, 
               -119.34345809131827, 27.05277412889302, 3.141595501113679, 0.02, 214.0813792651244, 78.33500007732427));
        right.setSegment(77, new Trajectory.Segment(79.77372377342111, 4.946418715160335, 
               -118.04582117769273, -27.0527804649646, 3.141595501113679, 0.02, 214.0813071373444, 103.66500007722156));


        left.setSegment(78, new Trajectory.Segment(66.5664662745242, 2.7447157836189517, 
               -110.78826518419984, 427.75964535592124, 3.1415929560495486, 0.02, 214.02648494945208, 78.33500000267112));
        right.setSegment(78, new Trajectory.Segment(79.82855362261911, 2.741492459899722, 
               -110.24631276303064, 389.9754207331043, 3.1415929560495486, 0.02, 214.02647728814645, 103.66500000266997));


        left.setSegment(79, new Trajectory.Segment(66.58830907045507, 1.092139796543287, 
               -82.62879935378324, 1407.9732915208303, 3.1415926628922497, 0.02, 214.00464215352122, 78.33500000001439));
        right.setSegment(79, new Trajectory.Segment(79.85038899287555, 1.0917685128220083, 
               -82.48619735388569, 1388.005770457248, 3.1415926628922497, 0.02, 214.00464191789, 103.6650000000144));


        left.setSegment(80, new Trajectory.Segment(66.59295122397629, 0.23210767606087757, 
               -43.00160602412048, 1981.359666483138, 3.1415926535897922, 0.02, 214, 78.33499999999998));
        right.setSegment(80, new Trajectory.Segment(79.85503091076556, 0.23209589450061685, 
               -42.98363091606957, 1975.1283218908056, 3.1415926535897922, 0.02, 214, 103.66499999999996));


        left.setSegment(81, new Trajectory.Segment(66.59295122397629, 0, 
               -11.605383803043878, 1569.8111110538298, 3.1415926535897922, 0.02, 214, 78.33499999999998));
        right.setSegment(81, new Trajectory.Segment(79.85503091076556, 0, 
               -11.604794725030843, 1568.9418095519363, 3.1415926535897922, 0.02, 214, 103.66499999999996));


    }


    public boolean isReversed() {
        return false; 
    }
  
	// WAYPOINT_DATA: [{"position":{"x":285,"y":81},"theta":-0.5235833333312303,"comment":""},{"position":{"x":214,"y":91},"theta":0,"comment":""}]
	// IS_REVERSED: false
	// FILE_NAME: RightSwitch20Degree
  // DT: 0.02
  // MAX_VEL: 140
  // MAX_ACC: 120
  // MAX_JERK: 2000
  // WHEEL_BASE: 25.33
  // PACKAGE: org.usfirst.frc862.glitch.paths
  // PARENT: org.usfirst.frc862.util.DynamicPathCommand
}