package org.usfirst.frc862.glitch.paths;
 
import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory;

// max path velocity 97.08289507147605
public class RightSwitchNeg20Degree extends org.usfirst.frc862.util.DynamicPathCommand {
    private static Path path;

    public Path getPath() {
        return RightSwitchNeg20Degree.generatePath();
    }

    public static Path generatePath() {
        if (path != null) {
            return path;
        }

        int num_elements = 82;
        Trajectory left = new Trajectory(num_elements);
        Trajectory right = new Trajectory(num_elements);

        build_segments_0(left, right);

       
        path = new Path("RightSwitchNeg20Degree", new Trajectory.Pair(left, right));
        return path;
    }


    private static void build_segments_0(Trajectory left, Trajectory right) {

        left.setSegment(0, new Trajectory.Segment(0.007881816512473102, 0.7881816512473101, 
               39.409082562365505, 1970.4541281182753, 3.316120356823223, 0.02, 287.1914269575907, 68.52602970091328));
        right.setSegment(0, new Trajectory.Segment(0.007881816512473102, 0.7881816512473101, 
               39.409082562365505, 1970.4541281182753, 3.316120356823223, 0.02, 282.79304888023125, 93.47123305323342));


        left.setSegment(1, new Trajectory.Segment(0.03943172210289719, 1.5774952795212047, 
               39.46568141369473, 2.829942566461341, 3.31611856925606, 0.02, 287.1603563335399, 68.52055130115991));
        right.setSegment(1, new Trajectory.Segment(0.03938644302666759, 1.5752313257097246, 
               39.35248372312072, -2.8299419622392463, 3.31611856925606, 0.02, 282.76202284741385, 93.46576251583642));


        left.setSegment(2, new Trajectory.Segment(0.11052905313311318, 3.554866551510799, 
               98.8685635994797, 2970.1441092892483, 3.3161058584457974, 0.02, 287.0903389780543, 68.50820624504709));
        right.setSegment(2, new Trajectory.Segment(0.11016180923291749, 3.538768310312495, 
               98.17684923013852, 2941.2182753508896, 3.3161058584457974, 0.02, 282.6923225661303, 93.45347336409085));


        left.setSegment(3, new Trajectory.Segment(0.22936133061020453, 5.941613873854567, 
               119.3373661171884, 1023.4401258854348, 3.31605808664983, 0.02, 286.9733111868418, 68.4875760183322));
        right.setSegment(3, new Trajectory.Segment(0.2277840271182635, 5.8811108942673, 
               117.11712919774024, 947.0139983800863, 3.31605808664983, 0.02, 282.57648646014695, 93.43305321005424));


        left.setSegment(4, new Trajectory.Segment(0.39642741065172404, 8.353304002075976, 
               120.58450641107048, 62.35701469410415, 3.315935865302298, 0.02, 286.8087796402114, 68.45858558432619));
        right.setSegment(4, new Trajectory.Segment(0.3917542404288401, 8.198510665528831, 
               115.86998856307655, -62.3570317331847, 3.315935865302298, 0.02, 282.41500381618613, 93.40459997557136));


        left.setSegment(5, new Trajectory.Segment(0.6124134908963553, 10.799304012231563, 
               122.30000050777932, 85.7747048354419, 3.315685013744243, 0.02, 286.5960633868309, 68.42114519961662));
        right.setSegment(5, new Trajectory.Segment(0.6013862507248161, 10.4816005147988, 
               114.15449246349843, -85.7748049789059, 3.315685013744243, 0.02, 282.2085454475598, 93.36826099147869));


        left.setSegment(6, new Trajectory.Segment(0.8781815011776819, 13.288400514066332, 
               124.45482509173846, 107.74122919795701, 3.3152374758377348, 0.02, 286.334302964956, 68.37516567342537));
        right.setSegment(6, new Trajectory.Segment(0.8558181259298707, 12.721593760252727, 
               111.99966227269637, -107.74150954010295, 3.3152374758377348, 0.02, 281.95795024467543, 93.32424254748413));


        left.setSegment(7, new Trajectory.Segment(1.1947549583488888, 15.828672858560344, 
               127.0136172247006, 127.93960664810697, 3.314512436746247, 0.02, 286.0224713931631, 68.32057790356365));
        right.setSegment(7, new Trajectory.Segment(1.1540263433187834, 14.910410869445634, 
               109.44085545964536, -127.94034065255033, 3.314512436746247, 0.02, 281.66420887761205, 93.27282124650864));


        left.setSegment(8, new Trajectory.Segment(1.563302763220368, 18.427390243573964, 
               129.93586925068098, 146.11260129901922, 3.3134176021482133, 0.02, 285.6593866207783, 68.25735660542846));
        right.setSegment(8, new Trajectory.Segment(1.4948419892142413, 17.04078229477289, 
               106.51857126636273, -146.11420966413178, 3.3134176021482133, 0.02, 281.3284452911235, 93.21435656934814));


        left.setSegment(9, new Trajectory.Segment(1.98512140865407, 21.090932271685098, 
               133.17710140555673, 162.0616077437873, 3.311850602820996, 0.02, 285.24372508763514, 68.18554811358672));
        right.setSegment(9, new Trajectory.Segment(1.876968545766709, 19.10632782762338, 
               103.27727664252464, -162.06473119190434, 3.311850602820996, 0.02, 280.95189666141215, 93.14930401609195));


        left.setSegment(10, new Trajectory.Segment(2.4616159991550366, 23.824729525048323, 
               136.68986266816123, 175.63806313022496, 3.3097004929784823, 0.02, 284.7740361792109, 68.10530209138048));
        right.setSegment(10, new Trajectory.Segment(2.299000864480263, 21.101615935677707, 
               99.76440540271625, -175.64356199041953, 3.3097004929784823, 0.02, 280.53589244940196, 93.07822818596301));


        left.setSegment(11, new Trajectory.Segment(2.9942803491119965, 26.633217497847987, 
               140.42439863998322, 186.7267985910999, 3.306849321232283, 0.02, 284.248757581387, 68.01690693097433));
        right.setSegment(11, new Trajectory.Segment(2.7594450586287014, 23.022209707421933, 
               96.0296885872113, -186.73584077524765, 3.306849321232283, 0.02, 280.0818330827155, 93.00181518020293));


        left.setSegment(12, new Trajectory.Segment(3.5846762307768723, 29.5197940832438, 
               144.3288292697906, 195.22153149036825, 3.303173768539136, 0.02, 283.6662318365894, 67.92082855809639));
        right.setSegment(12, new Trajectory.Segment(3.2567392430885307, 24.864709222991472, 
               92.12497577847695, -195.23564043671726, 3.303173768539136, 0.02, 279.59116862484063, 92.9208837544405));


        left.setSegment(13, new Trajectory.Segment(4.234411589314376, 32.486767926875174, 
               148.3486921815688, 200.99314558891024, 3.2985468676255367, 0.02, 283.02472477281174, 67.81775225986446));
        right.setSegment(13, new Trajectory.Segment(3.7892753061987414, 26.62680315551053, 
               88.10469662595288, -201.01395762620342, 3.2985468676255367, 0.02, 279.0653775463977, 92.83639469986683));


        left.setSegment(14, new Trajectory.Segment(4.945117236062277, 35.535282337395074, 
               152.425720525995, 203.85141722130982, 3.292839842427622, 0.02, 282.32244691025, 67.70862701037993));
        right.setSegment(14, new Trajectory.Segment(4.355422201127845, 28.307344746455172, 
               84.02707954723212, -203.88085393603816, 3.292839842427622, 0.02, 278.5059454096533, 92.74945799291609));


        left.setSegment(15, new Trajectory.Segment(5.7184211932735005, 38.665197860561186, 
               156.49577615830557, 203.50278161552922, 3.2859241328065703, 0.02, 281.55757942030544, 67.59471154582633));
        right.setSegment(15, new Trajectory.Segment(4.953551583116444, 29.906469099429955, 
               79.95621764873917, -203.5430949246475, 3.2859241328065703, 0.02, 277.9143429206686, 92.66133732209931));


        left.setSegment(16, new Trajectory.Segment(6.555919518403117, 41.874916256480816, 
               160.4859197959815, 199.50718188379568, 3.2776736972893135, 0.02, 280.7283066836247, 67.47762109876548));
        right.setSegment(16, new Trajectory.Segment(5.582066969873377, 31.425769337846674, 
               75.96501192083593, -199.56028639516177, 3.2776736972893135, 0.02, 277.29200241362236, 92.57345168800806));


        left.setSegment(17, new Trajectory.Segment(7.459142108922163, 45.161129525952276, 
               164.31066347357302, 191.23718387957638, 3.267967712091975, 0.02, 279.8328579238708, 67.35937319527812));
        right.setSegment(17, new Trajectory.Segment(6.239437921126953, 32.868547562678785, 
               72.13891124160554, -191.30503396151965, 3.267967712091975, 0.02, 276.6402914488735, 92.48737389773598));


        left.setSegment(18, new Trajectory.Segment(8.42951174973287, 48.51848204053534, 
               167.86762572915313, 177.84811277900587, 3.256693803863337, 0.02, 278.8695606823009, 67.24243019980183));
        right.setSegment(18, new Trajectory.Segment(6.924240979811362, 34.24015293422043, 
               68.58026857708239, -177.93213322615742, 3.256693803863337, 0.02, 275.96048186719753, 92.4048259610865));


        left.setSegment(19, new Trajectory.Segment(9.468294576957447, 51.939141361228856, 
               171.03296603467584, 158.26701527613523, 3.2437519604853793, 0.02, 277.83690893623606, 67.12973533085969));
        right.setSegment(19, new Trajectory.Segment(7.635209203249318, 35.54841117189783, 
               65.41291188387, -158.36783466061988, 3.2437519604853793, 0.02, 275.253712434947, 92.32767167167395));


        left.setSegment(20, new Trajectory.Segment(10.57654030863017, 55.41228658363614, 
               173.65726112036413, 131.21475428441443, 3.2290592505467455, 0.02, 276.73364827222736, 67.02473766887672));
        right.setSegment(20, new Trajectory.Segment(8.371291941172892, 36.80413689617865, 
               62.78628621404074, -131.3312834914626, 3.2290592505467455, 0.02, 274.52094324001644, 92.2579070385859));


        left.setSegment(21, new Trajectory.Segment(11.755011152493452, 58.92354219316408, 
               175.56278047639714, 95.27596780165055, 3.2125554375809666, 0.02, 275.55887950039084, 66.9314003100456));
        right.setSegment(21, new Trajectory.Segment(9.131725948555362, 38.0217003691235, 
               60.87817364724266, -95.40562833990407, 3.2125554375809666, 0.02, 273.76290040824915, 92.19764971712203));


        left.setSegment(22, new Trajectory.Segment(13.00409940788765, 62.4544127697099, 
               176.54352882729077, 49.037417544681716, 3.194209488289726, 0.02, 274.31218018461885, 66.85418447088439));
        right.setSegment(22, new Trajectory.Segment(9.916117827007271, 39.21959392259551, 
               59.89467767360033, -49.174798682116716, 3.194209488289726, 0.02, 272.98001065244335, 92.1491291108227));


        left.setSegment(23, new Trajectory.Segment(14.323735529275577, 65.98180606939631, 
               176.3696649843208, -8.693192148498952, 3.1740268356040295, 0.02, 272.993740595244, 66.79800135917975));
        right.setSegment(23, new Trajectory.Segment(10.724536034211578, 40.42091036021538, 
               60.06582188099365, 8.55721036966628, 3.1740268356040295, 0.02, 272.1723268004561, 92.11467924901618));


        left.setSegment(24, new Trajectory.Segment(15.713290845284085, 69.4777658004254, 
               174.79798655145444, -78.58392164331747, 3.152057065213801, 0.02, 271.60450652916387, 66.76812350969223));
        right.setSegment(24, new Trajectory.Segment(11.557608259340677, 41.65361125645493, 
               61.63504481197748, 78.46114654919134, 3.152057065213801, 0.02, 271.33944782028374, 92.09673665531957));


        left.setSegment(25, new Trajectory.Segment(17.171482108865458, 72.90956317906857, 
               171.5898689321584, -160.40588096480235, 3.128401459539148, 0.02, 270.1463165364324, 66.77004868053444));
        right.setSegment(25, new Trajectory.Segment(12.416617002989327, 42.950437182432466, 
               64.84129629887683, 160.31257434496737, 3.128401459539148, 0.02, 270.48043979154085, 92.09784490623073));


        left.setSegment(26, new Trajectory.Segment(18.696288100914956, 76.24029960247495, 
               166.53682117031892, -252.652388091974, 3.1032195908711815, 0.02, 268.6220161623617, 66.80931295180102));
        right.setSegment(26, new Trajectory.Segment(13.303583115415929, 44.34830562133016, 
               69.89342194488479, 252.60628230039828, 3.1032195908711815, 0.02, 269.59376731742435, 92.12066612962687));


        left.setSegment(27, new Trajectory.Segment(20.28489093529819, 79.43014171916171, 
               159.4921058343381, -352.23576679904056, 3.076733964587655, 0.02, 267.0355281063107, 66.89125569655599));
        right.setSegment(27, new Trajectory.Segment(14.221324644905746, 45.88707647449088, 
               76.93854265803601, 352.25603565756103, 3.076733964587655, 0.02, 268.6772471108412, 92.16799715407308));


        left.setSegment(28, new Trajectory.Segment(21.933655418814702, 82.43822417582554, 
               150.40412283319142, -454.3991500573341, 3.0492316559606367, 0.02, 265.39185653004694, 67.02074728503953));
        right.setSegment(28, new Trajectory.Segment(15.173477605645836, 47.60764803700449, 
               86.02857812568026, 454.5017733822121, 3.0492316559606367, 0.02, 267.728035807505, 92.24278460131467));


        left.setSegment(29, new Trajectory.Segment(23.638157852290036, 85.22512167376662, 
               139.34487489705418, -552.962396806862, 3.021062039905143, 0.02, 263.69700773327776, 67.20189943604359));
        right.setSegment(29, new Trajectory.Segment(16.164467257013854, 49.54948256840086, 
               97.0917265698187, 553.1574222069224, 3.021062039905143, 0.02, 266.74266130590564, 92.34812965680507));


        left.setSegment(30, new Trajectory.Segment(25.393270395850518, 87.75562717802411, 
               126.52527521287453, -640.9799842089825, 2.9926301283552106, 0.02, 261.9578189332195, 67.4377857610295));
        right.setSegment(30, new Trajectory.Segment(17.199423740316586, 51.74782416513667, 
               109.91707983679042, 641.2676633485859, 2.9926301283552106, 0.02, 265.71710064797617, 92.48727080541907));


        left.setSegment(31, new Trajectory.Segment(27.19329895265493, 90.00142784022069, 
               112.29003310982861, -711.7621051522959, 2.964385675770242, 0.02, 260.18170106918404, 67.73020350735385));
        right.setSegment(31, new Trajectory.Segment(18.284044098363132, 54.23101790232726, 
               124.15968685952947, 712.1303511369525, 2.964385675770242, 0.02, 264.64689832907214, 92.66353287649923));


        left.setSegment(32, new Trajectory.Segment(29.032162996323013, 91.9432021834041, 
               97.08871715917056, -760.0657975329028, 2.93680896702044, 0.02, 258.3763175382826, 68.079504634528));
        right.setSegment(32, new Trajectory.Segment(19.42441225096736, 57.01840763021148, 
               139.3694863942109, 760.4899767340711, 2.93680896702044, 0.02, 263.527309088333, 92.88023418128347));


        left.setSegment(33, new Trajectory.Segment(30.903597353622004, 93.57171786494955, 
               81.42578407727257, -783.1466540948994, 2.9103948675825158, 0.02, 256.54923424617243, 68.48451475461266));
        right.setSegment(33, new Trajectory.Segment(20.626796931923668, 60.119234047815404, 
               155.0413208801963, 783.5917242992707, 2.9103948675825158, 0.02, 262.3534417279276, 93.14055003015397));


        left.setSegment(34, new Trajectory.Segment(32.80135124444884, 94.88769454134155, 
               65.79883381959988, -781.3475128836344, 2.8856370888540894, 0.02, 254.707583105451, 68.9425441270636));
        right.setSegment(34, new Trajectory.Segment(21.89745231939931, 63.53276937378204, 
               170.67676629833173, 781.7722709067709, 2.8856370888540894, 0.02, 261.12037814797213, 93.44733875469956));


        left.setSegment(35, new Trajectory.Segment(34.7193605233539, 95.90046394525291, 
               50.638470195568175, -758.0181812015851, 2.8630145709572488, 0.02, 252.85777882068618, 69.44947972655493));
        right.setSegment(35, new Trajectory.Segment(23.242445457979226, 67.24965692899579, 
               185.8443777606876, 758.3805731177932, 2.8630145709572488, 0.02, 259.8232458397808, 93.8029429136646));


        left.setSegment(36, new Trajectory.Segment(36.65187506096123, 96.62572688036643, 
               36.26314675567599, -718.7661719946092, 2.8429814114793213, 0.02, 251.00531812721067, 69.99993573496242));
        right.setSegment(36, new Trajectory.Segment(24.66752857186374, 71.25415569422566, 
               200.22493826149343, 719.028025040292, 2.8429814114793213, 0.02, 258.4572316558518, 94.20898125025805));


        left.setSegment(37, new Trajectory.Segment(38.59353296239075, 97.08289507147605, 
               22.85840955548082, -670.2368600097585, 2.8259609968570554, 0.02, 249.15467489027216, 70.58743468454229));
        right.setSegment(37, new Trajectory.Segment(26.178064595457567, 75.52680117969129, 
               213.63227427328155, 670.3668005894059, 2.8259609968570554, 0.02, 257.0175371536311, 94.66614513646321));


        left.setSegment(38, new Trajectory.Segment(40.53084725106298, 96.86571443361149, 
               -10.859031893227922, -1685.872072435437, 2.812395874688488, 0.02, 247.31736684655806, 71.20183341956462));
        right.setSegment(38, new Trajectory.Segment(27.771776992553896, 79.68561985481647, 
               207.94093375625877, -284.567025851139, 2.812395874688488, 0.02, 255.50612632006226, 95.17167109705204));


        left.setSegment(39, new Trajectory.Segment(42.44333049113706, 95.624162003704, 
               -62.0776214953743, -2560.929480107319, 2.802658924539844, 0.02, 245.51080885597196, 71.829480111752));
        right.setSegment(39, new Trajectory.Segment(29.437624273741903, 83.29236405940028, 
               180.33721022919096, -1380.1861763533907, 2.802658924539844, 0.02, 253.93256957840163, 95.71844924677625));


        left.setSegment(40, new Trajectory.Segment(44.30818552765685, 93.24275182598967, 
               -119.07050888571646, -2849.644369517108, 2.796955959639709, 0.02, 243.75402390498573, 72.45509256624884));
        right.setSegment(40, new Trajectory.Segment(31.15802341333688, 86.01995697974891, 
               136.37964601743136, -2197.87821058798, 2.796955959639709, 0.02, 252.31188488790195, 96.29564447674538));


        left.setSegment(41, new Trajectory.Segment(46.10530333906259, 89.85589057028672, 
               -169.34306278514768, -2513.6276949715607, 2.7952805094789284, 0.02, 242.0632862585068, 73.06422197684284));
        right.setSegment(41, new Trajectory.Segment(32.91270207941557, 87.73393330393432, 
               85.69881620927049, -2534.041490408043, 2.7952805094789284, 0.02, 250.66107886774145, 96.8904021626928));


        left.setSegment(42, new Trajectory.Segment(47.82142397542643, 85.80603181819201, 
               -202.49293760473535, -1657.4937409793833, 2.797434696321884, 0.02, 240.44860732045754, 73.6454981294364));
        right.setSegment(42, new Trajectory.Segment(34.68338825532057, 88.5343087952504, 
               40.01877456580374, -2284.0020821733374, 2.797434696321884, 0.02, 248.99505397640388, 97.4901442697561));


        left.setSegment(43, new Trajectory.Segment(49.4493631742865, 81.39695994300389, 
               -220.45359375940592, -898.0328077335287, 2.8030903354389607, 0.02, 238.91474810453005, 74.19089884533206));
        right.setSegment(43, new Trajectory.Segment(36.454584596089, 88.55981703842146, 
               1.2754121585530243, -1937.1681203625358, 2.8030903354389607, 0.02, 247.32620208191636, 98.08349899655694));


        left.setSegment(44, new Trajectory.Segment(50.99046337646102, 77.05501010872587, 
               -217.0974917139013, 167.80510227523138, 2.811868384508193, 0.02, 237.45901229315112, 74.69668840691995));
        right.setSegment(44, new Trajectory.Segment(38.218032059460135, 88.17237316855697, 
               -19.372193493224188, -1032.3802825888606, 2.811868384508193, 0.02, 245.66041448082282, 98.66220325964227));


        left.setSegment(45, new Trajectory.Segment(52.449566352215875, 72.9551487877425, 
               -204.99306604916825, 605.2212832366521, 2.8233865213125524, 0.02, 236.0759338926999, 75.1615277684869));
        right.setSegment(45, new Trajectory.Segment(39.96888781919411, 87.54278798669883, 
               -31.47925909290734, -605.3532799841577, 2.8233865213125524, 0.02, 244.00076008156927, 99.21991570027092));


        left.setSegment(46, new Trajectory.Segment(53.831568760410384, 69.10012040972548, 
               -192.75141890085123, 612.0823574158507, 2.837258124716365, 0.02, 234.76044584262877, 75.58510992861108));
        right.setSegment(46, new Trajectory.Segment(41.702255116276646, 86.66836485412695, 
               -43.72115662859386, -612.094876784326, 2.837258124716365, 0.02, 242.35079187744373, 99.75110974388394));


        left.setSegment(47, new Trajectory.Segment(55.14141734875467, 65.49242941721435, 
               -180.3845496255562, 618.3434637647522, 2.8530926429874275, 0.02, 233.50780411616475, 75.96798331435205));
        right.setSegment(47, new Trajectory.Segment(43.41318785494552, 85.54663693344371, 
               -56.08639603416208, -618.2619702784109, 2.8530926429874275, 0.02, 240.71455762456762, 100.2511380490467));


        left.setSegment(48, new Trajectory.Segment(56.384084211247746, 62.13334312465375, 
               -167.95431462803023, 621.5117498762978, 2.8704975383340336, 0.02, 232.31352811656444, 76.31138652912598));
        right.setSegment(48, new Trajectory.Segment(45.096715146057896, 84.17636455561889, 
               -68.51361889124092, -621.3611428539423, 2.8704975383340336, 0.02, 239.09656565176743, 100.7162897617968));


        left.setSegment(49, new Trajectory.Segment(57.56451848806847, 59.021713841036174, 
               -155.58146418087873, 618.6425223575753, 2.8890820928422425, 0.02, 231.17336630448565, 76.61709247308272));
        right.setSegment(49, new Trajectory.Segment(46.747889409710254, 82.558713182618, 
               -80.8825686500441, -618.4474879401591, 2.8890820928422425, 0.02, 237.501704345111, 101.14383374956651));


        left.setSegment(50, new Trajectory.Segment(58.687576095268035, 56.15288035997806, 
               -143.44167405290554, 606.9895063986593, 2.908462950628821, 0.02, 230.08329004760176, 76.88726305487654));
        right.setSegment(50, new Trajectory.Segment(48.36185645858762, 80.698352443868, 
               -93.01803693750017, -606.7734143728031, 2.908462950628821, 0.02, 235.93512008075876, 101.53203907867197));


        left.setSegment(51, new Trajectory.Segment(59.757933369892356, 53.51786373121593, 
               -131.75083143810653, 584.5421307399505, 2.9282709313339246, 0.02, 229.0395133726085, 77.12431779134303));
        right.setSegment(51, new Trajectory.Segment(49.933941680385935, 78.60426108991582, 
               -104.70456769760901, -584.3265380054419, 2.9282709313339246, 0.02, 234.40206411641418, 101.8801645657943));


        left.setSegment(52, new Trajectory.Segment(60.7799934565362, 51.103004332191915, 
               -120.74296995120086, 550.3930743452834, 2.948158418094686, 0.02, 228.03853205637978, 77.33082015276158));
        right.setSegment(52, new Trajectory.Segment(51.459743504310126, 76.29009119620943, 
               -115.70849468531961, -550.19634938553, 2.948158418094686, 0.02, 232.9077232364366, 102.18841209671417));


        left.setSegment(53, new Trajectory.Segment(61.75779545550467, 48.890099948423845, 
               -110.64521918840349, 504.88753813986875, 2.967806528943641, 0.02, 227.0771728718165, 77.50938461575058));
        right.setSegment(53, new Trajectory.Segment(52.9352241454143, 73.77403205520888, 
               -125.80295705002769, -504.72311823540394, 2.967806528943641, 0.02, 231.45705091279078, 102.45784285160536));


        left.setSegment(54, new Trajectory.Segment(62.69493570891679, 46.85701267060604, 
               -101.65436389089031, 449.5427648756589, 2.986931330962434, 0.02, 226.15264314404968, 77.66260549239851));
        right.setSegment(54, new Trajectory.Segment(54.35678825128066, 71.07820529331788, 
               -134.79133809454993, -449.4190522261121, 2.986931330962434, 0.02, 230.05461498259373, 102.6902605118207));


        left.setSegment(55, new Trajectory.Segment(63.594508427697946, 44.97863593905808, 
               -93.91883657739797, 386.776365674617, 3.005288528366666, 0.02, 225.2625719777826, 77.79300639483144));
        right.setSegment(55, new Trajectory.Segment(55.72134225137225, 68.22770000457936, 
               -142.52526443692588, -386.6963171187976, 3.005288528366666, 0.02, 228.7044745783581, 102.88806978363665));


        left.setSegment(56, new Trajectory.Segment(64.45906975924072, 43.228066577138875, 
               -87.52846809596022, 319.51842407188735, 3.0226763005150366, 0.02, 224.40503708527908, 77.90300732272738));
        right.setSegment(56, new Trajectory.Segment(57.02633030306536, 65.24940258465539, 
               -148.9148709961988, -319.480327963646, 3.0226763005150366, 0.02, 227.4100941383659, 103.05412125646185));


        left.setSegment(57, new Trajectory.Segment(65.2906261100652, 41.57781754122396, 
               -82.51245179574589, 250.8008150107166, 3.038936225318946, 0.02, 223.57857430095152, 77.99490525914632));
        right.setSegment(57, new Trajectory.Segment(58.26974601177095, 62.1707854352796, 
               -153.93085746878938, -250.79932362952917, 3.038936225318946, 0.02, 226.17429691529992, 103.1915543242375));


        left.setSegment(58, new Trajectory.Segment(66.09064471958638, 40.00093047605902, 
               -78.84435325824697, 183.40492687494603, 3.0539524459919454, 0.02, 222.78216984737034, 78.07086394460278));
        right.setSegment(58, new Trajectory.Segment(59.45012191690214, 59.01879525655976, 
               -157.59950893599174, -183.4325733601176, 3.0539524459919454, 0.02, 224.99925559291913, 103.30364878490299));


        left.setSegment(59, new Trajectory.Segment(66.86008254654948, 38.471891348155154, 
               -76.45195639519322, 119.61984315268737, 3.0676493919770658, 0.02, 222.01523766116935, 78.13290901959405));
        right.setSegment(59, new Trajectory.Segment(60.56650067310053, 55.81893780991922, 
               -159.99287233202713, -119.66816980176986, 3.0676493919770658, 0.02, 223.88651415569973, 103.39369333602053));


        left.setSegment(60, new Trajectory.Segment(67.59942859881951, 36.967302613501836, 
               -75.22943673266589, 61.125983126366634, 3.0799884382879807, 0.02, 221.27758535933626, 78.18292568289365));
        right.setSegment(60, new Trajectory.Segment(61.61839278510429, 52.59460560018812, 
               -161.2166104865551, -61.18690772639894, 3.0799884382879807, 0.02, 222.83703332457137, 103.46487620186517));


        left.setSegment(61, new Trajectory.Segment(68.30875480042798, 35.46631008042287, 
               -75.04962665394821, 8.990503935883964, 3.090963890608569, 0.02, 220.56937276750241, 78.2226570943495));
        right.setSegment(61, new Trajectory.Segment(62.6057257980686, 49.36665064821546, 
               -161.39774759863278, -9.056855603883207, 3.090963890608569, 0.02, 221.85125153608774, 103.5202001928927));


        left.setSegment(62, new Trajectory.Segment(68.9877710861512, 33.95081428616104, 
               -75.77478971309155, -36.258152957167056, 3.100598636341121, 0.02, 219.89106657890912, 78.25370271873352));
        right.setSegment(62, new Trajectory.Segment(63.52878924876654, 46.15317253489741, 
               -160.67390566590268, 36.1920966365048, 3.100598636341121, 0.02, 220.92915422611955, 103.56242204699485));


        left.setSegment(63, new Trajectory.Segment(69.63588135583267, 32.40551348407355, 
               -77.26504010437445, -74.51251956414495, 3.1089397275018777, 0.02, 219.24339395798086, 78.27751653334826));
        right.setSegment(63, new Trajectory.Segment(64.38817874465965, 42.96947479465533, 
               -159.1848870121037, 74.45093268994896, 3.1089397275018777, 0.02, 220.07034560632633, 103.59401413809798));


        left.setSegment(64, new Trajectory.Segment(70.25223795131524, 30.81782977412839, 
               -79.38418549725803, -105.95726964417906, 3.1160540799491807, 0.02, 218.62729701871692, 78.29540547474977));
        right.setSegment(64, new Trajectory.Segment(65.1847415072697, 39.828138130502886, 
               -157.06683320762238, 105.90269022406602, 3.1160540799491807, 0.02, 219.27411877213788, 103.6171455733153));


        left.setSegment(65, new Trajectory.Segment(70.83579326219152, 29.177765543813788, 
               -82.00321151573017, -130.95130092360705, 3.1220243991452907, 0.02, 218.04388928691543, 78.3085287108656));
        right.setSegment(65, new Trajectory.Segment(65.9195247785409, 36.739163563560176, 
               -154.4487283471355, 130.90524302434403, 3.1220243991452907, 0.02, 218.53952153961353, 103.63367922710313));


        left.setSegment(66, new Trajectory.Segment(71.38534781419985, 27.47772760041623, 
               -85.00189716987786, -149.93428270738463, 3.126945383331349, 0.02, 217.49441461467518, 78.31789835331017));
        right.setSegment(66, new Trajectory.Segment(66.59372773404385, 33.71014777514741, 
               -151.4507894206382, 149.8969463248656, 3.126945383331349, 0.02, 217.86541670403528, 103.64518122079699));


        left.setSegment(67, new Trajectory.Segment(71.89959472336699, 25.71234545835702, 
               -88.26910710296048, -163.36049665413057, 3.130920213534262, 0.02, 216.98020858185868, 78.3243821333088));
        right.setSegment(67, new Trajectory.Segment(67.20865702586076, 30.746464590845097, 
               -148.18415921511576, 163.3315102761216, 3.130920213534262, 0.02, 217.25053635663085, 103.65293959113072));


        left.setSegment(68, new Trajectory.Segment(72.37716070773328, 23.878299218314925, 
               -91.70231200210476, -171.6602449572143, 3.134057315231905, 0.02, 216.50266219373796, 78.32870840382873));
        right.setSegment(68, new Trajectory.Segment(67.7656857635757, 27.85143688574712, 
               -144.75138525489885, 171.6386980108453, 3.134057315231905, 0.02, 216.69353050803872, 103.65798927176093));


        left.setSegment(69, new Trajectory.Segment(72.81664398207532, 21.97416371710208, 
               -95.20677506064227, -175.2231529268755, 3.1364673660391063, 0.02, 216.06318761891794, 78.33147363675931));
        right.setSegment(69, new Trajectory.Segment(68.26621561005076, 25.026492323752834, 
               -141.2472280997143, 175.20785775922718, 3.1364673660391063, 0.02, 216.1930105841978, 103.66114094601716));


        left.setSegment(70, new Trajectory.Segment(73.21664936187139, 20.000268989803608, 
               -98.69473636492359, -174.39806521406567, 3.138260523696093, 0.02, 215.6631857618157, 78.33315238050378));
        right.setSegment(70, new Trajectory.Segment(68.71164166719234, 22.271302857078993, 
               -137.75947333369203, 174.38773830111387, 3.138260523696093, 0.02, 215.74758845583446, 103.66301176000374));


        left.setSegment(71, new Trajectory.Segment(73.57582081931014, 17.9585728719375, 
               -102.08480589330549, -169.50347641909502, 3.139543855239418, 0.02, 215.30401557949816, 78.3341094451097));
        right.setSegment(71, new Trajectory.Segment(69.1033199103829, 19.583912159527767, 
               -134.3695348775613, 169.49692280653608, 3.139543855239418, 0.02, 215.35591160540687, 103.66405628284497));


        left.setSegment(72, new Trajectory.Segment(73.89287160694727, 15.852539381856396, 
               -105.30167450405514, -160.84343053748285, 3.1404189582182878, 0.02, 214.98696519317616, 78.33461389966364));
        right.setSegment(72, new Trajectory.Segment(69.44253705576324, 16.960857269017097, 
               -131.15274452553348, 160.83951760139144, 3.1404189582182878, 0.02, 215.01669489011067, 103.6645964528578));


        left.setSegment(73, new Trajectory.Segment(74.16661192957153, 13.687016131213502, 
               -108.27616253214467, -148.72440140447623, 3.140979775566916, 0.02, 214.71322497612053, 78.33485430926162));
        right.setSegment(73, new Trajectory.Segment(69.73048288164058, 14.397291293867323, 
               -128.17829875748873, 148.72228840223727, 3.140979775566916, 0.02, 214.72874917546815, 103.66484955204116));


        left.setSegment(74, new Trajectory.Segment(74.39597402170388, 11.468104606617162, 
               -110.945576229817, -133.4706848836163, 3.141310615336334, 0.02, 214.48386290587771, 78.3349545153284));
        right.setSegment(74, new Trajectory.Segment(69.96822514509363, 11.88711317265189, 
               -125.50890606077161, 133.46963483585625, 3.141310615336334, 0.02, 214.49100693474313, 103.6649535078837));


        left.setSegment(75, new Trajectory.Segment(74.58003439767728, 9.203018798670444, 
               -113.25429039733592, -115.43570837594643, 3.1414843937476236, 0.02, 214.29980253316893, 78.3349891819648));
        right.setSegment(75, new Trajectory.Segment(70.15668732821939, 9.423109156287934, 
               -123.20020081819783, 115.43526212868898, 3.1414843937476236, 0.02, 214.3025447549657, 103.66498903352854));


        left.setSegment(76, new Trajectory.Segment(74.71803298528941, 6.899929380606634, 
               -115.15447090319046, -95.00902529272679, 3.1415611243484607, 0.02, 214.161803945858, 78.33499829956025));
        right.setSegment(76, new Trajectory.Segment(70.2966295019502, 6.997108686540941, 
               -121.30002348734962, 95.00886654241043, 3.1415611243484607, 0.02, 214.16260258154082, 103.66499828697008));


        left.setSegment(77, new Trajectory.Segment(74.80938883809895, 4.5677926404773865, 
               -116.60683700646238, -72.61830516359637, 3.1415866773540335, 0.02, 214.07044809306177, 78.33499985966495));
        right.setSegment(77, new Trajectory.Segment(70.38863261239091, 4.600155522035249, 
               -119.84765822528462, 72.61826310325006, 3.1415866773540335, 0.02, 214.07059947111355, 103.6649998592126));


        left.setSegment(78, new Trajectory.Segment(74.85813354259152, 2.437235224628435, 
               -106.52787079244759, 503.9483107007399, 3.1415920864135565, 0.02, 214.0217033885694, 78.33499999589684));
        right.setSegment(78, new Trajectory.Segment(70.43751432836117, 2.444085798513702, 
               -107.80348617607733, 602.2086024603645, 3.1415920864135565, 0.02, 214.02171775514347, 103.66499999589276));


        left.setSegment(79, new Trajectory.Segment(74.87637992457105, 0.9123190989768888, 
               -76.24580628257729, 1514.1032254935146, 3.141592639200044, 0.02, 214.00345700658985, 78.33499999998342));
        right.setSegment(79, new Trajectory.Segment(70.45577471242245, 0.9130192030639479, 
               -76.55332977248771, 1562.5078201794806, 3.141592639200044, 0.02, 214.0034573710822, 103.66499999998341));


        left.setSegment(80, new Trajectory.Segment(74.87983693116091, 0.17285032949274637, 
               -36.97343847420712, 1963.6183904185084, 3.1415926535897927, 0.02, 214, 78.33499999999998));
        right.setSegment(80, new Trajectory.Segment(70.45923208350465, 0.17286855410958424, 
               -37.007532447718184, 1977.2898662384764, 3.1415926535897927, 0.02, 214, 103.66499999999999));


        left.setSegment(81, new Trajectory.Segment(74.87983693116091, 0, 
               -8.642516474637318, 1416.54609997849, 3.1415926535897927, 0.02, 214, 78.33499999999998));
        right.setSegment(81, new Trajectory.Segment(70.45923208350465, 0, 
               -8.643427705479212, 1418.2052371119485, 3.1415926535897927, 0.02, 214, 103.66499999999999));


    }


    public boolean isReversed() {
        return false; 
    }
  
	// WAYPOINT_DATA: [{"position":{"x":285,"y":81},"theta":0.17452777777707676,"comment":""},{"position":{"x":214,"y":91},"theta":0,"comment":""}]
	// IS_REVERSED: false
	// FILE_NAME: RightSwitchNeg20Degree
  // DT: 0.02
  // MAX_VEL: 140
  // MAX_ACC: 120
  // MAX_JERK: 2000
  // WHEEL_BASE: 25.33
  // PACKAGE: org.usfirst.frc862.glitch.paths
  // PARENT: org.usfirst.frc862.util.DynamicPathCommand
}