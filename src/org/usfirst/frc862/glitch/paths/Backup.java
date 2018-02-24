package org.usfirst.frc862.glitch.paths;
 
import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.Trajectory;

public class Backup extends org.usfirst.frc862.util.DynamicPathCommand {
    private static Path path;

    public Path getPath() {
        return Backup.generatePath();
    }

    public static Path generatePath() {
        if (path != null) {
            return path;
        }

        int num_elements = 96;
        Trajectory left = new Trajectory(num_elements);
        Trajectory right = new Trajectory(num_elements);

        build_segments_0(left, right);

       
        path = new Path("Backup", new Trajectory.Pair(left, right));
        return path;
    }


    private static void build_segments_0(Trajectory left, Trajectory right) {

        left.setSegment(0, new Trajectory.Segment(0.002360438074486298, 0.23604380744862982, 
               11.802190372431491, 590.1095186215746, 0, 0.02, 20.002360438074486, 232.5));
        right.setSegment(0, new Trajectory.Segment(0.002360438074486298, 0.23604380744862982, 
               11.802190372431491, 590.1095186215746, 0, 0.02, 20.002360438074486, 207.5));


        left.setSegment(1, new Trajectory.Segment(0.011802190372431826, 0.47208761489727635, 
               11.802190372432326, 4.1744385725905886e-11, 0, 0.02, 20.011802190372432, 232.5));
        right.setSegment(1, new Trajectory.Segment(0.011802190372431826, 0.47208761489727635, 
               11.802190372432326, 4.1744385725905886e-11, 0, 0.02, 20.011802190372432, 207.5));


        left.setSegment(2, new Trajectory.Segment(0.03304613304280926, 1.0621971335188718, 
               29.505475931079772, 885.1642779323722, 0, 0.02, 20.03304613304281, 232.5));
        right.setSegment(2, new Trajectory.Segment(0.03304613304280926, 1.0621971335188718, 
               29.505475931079772, 885.1642779323722, 0, 0.02, 20.03304613304281, 207.5));


        left.setSegment(3, new Trajectory.Segment(0.07081314223458782, 1.8883504595889278, 
               41.3076663035028, 590.1095186211513, 0, 0.02, 20.070813142234588, 232.5));
        right.setSegment(3, new Trajectory.Segment(0.07081314223458782, 1.8883504595889278, 
               41.3076663035028, 590.1095186211513, 0, 0.02, 20.070813142234588, 207.5));


        left.setSegment(4, new Trajectory.Segment(0.12982409409674736, 2.950547593107977, 
               53.10985667595247, 590.1095186224836, 0, 0.02, 20.129824094096747, 232.5));
        right.setSegment(4, new Trajectory.Segment(0.12982409409674736, 2.950547593107977, 
               53.10985667595247, 590.1095186224836, 0, 0.02, 20.129824094096747, 207.5));


        left.setSegment(5, new Trajectory.Segment(0.21243942670376717, 4.1307666303509905, 
               59.01095186215066, 295.0547593099095, 0, 0.02, 20.212439426703767, 232.5));
        right.setSegment(5, new Trajectory.Segment(0.21243942670376717, 4.1307666303509905, 
               59.01095186215066, 295.0547593099095, 0, 0.02, 20.212439426703767, 207.5));


        left.setSegment(6, new Trajectory.Segment(0.3186591400556543, 5.310985667594359, 
               59.010951862168426, 8.881784197001252e-10, 0, 0.02, 20.318659140055654, 232.5));
        right.setSegment(6, new Trajectory.Segment(0.3186591400556543, 5.310985667594359, 
               59.010951862168426, 8.881784197001252e-10, 0, 0.02, 20.318659140055654, 207.5));


        left.setSegment(7, new Trajectory.Segment(0.4484832341524053, 6.49120470483755, 
               59.010951862159544, -4.440892098500626e-10, 0, 0.02, 20.448483234152405, 232.5));
        right.setSegment(7, new Trajectory.Segment(0.4484832341524053, 6.49120470483755, 
               59.010951862159544, -4.440892098500626e-10, 0, 0.02, 20.448483234152405, 207.5));


        left.setSegment(8, new Trajectory.Segment(0.6019117089940237, 7.671423742080918, 
               59.010951862168426, 4.440892098500626e-10, 0, 0.02, 20.601911708994024, 232.5));
        right.setSegment(8, new Trajectory.Segment(0.6019117089940237, 7.671423742080918, 
               59.010951862168426, 4.440892098500626e-10, 0, 0.02, 20.601911708994024, 207.5));


        left.setSegment(9, new Trajectory.Segment(0.7789445645805023, 8.851642779323932, 
               59.01095186215066, -8.881784197001252e-10, 0, 0.02, 20.778944564580502, 232.5));
        right.setSegment(9, new Trajectory.Segment(0.7789445645805023, 8.851642779323932, 
               59.01095186215066, -8.881784197001252e-10, 0, 0.02, 20.778944564580502, 207.5));


        left.setSegment(10, new Trajectory.Segment(0.9795818009118448, 10.031861816567123, 
               59.010951862159544, 4.440892098500626e-10, 0, 0.02, 20.979581800911845, 232.5));
        right.setSegment(10, new Trajectory.Segment(0.9795818009118448, 10.031861816567123, 
               59.010951862159544, 4.440892098500626e-10, 0, 0.02, 20.979581800911845, 207.5));


        left.setSegment(11, new Trajectory.Segment(1.2038234179880547, 11.212080853810491, 
               59.010951862168426, 4.440892098500626e-10, 0, 0.02, 21.203823417988055, 232.5));
        right.setSegment(11, new Trajectory.Segment(1.2038234179880547, 11.212080853810491, 
               59.010951862168426, 4.440892098500626e-10, 0, 0.02, 21.203823417988055, 207.5));


        left.setSegment(12, new Trajectory.Segment(1.4516694158091248, 12.392299891053504, 
               59.01095186215066, -8.881784197001252e-10, 0, 0.02, 21.451669415809125, 232.5));
        right.setSegment(12, new Trajectory.Segment(1.4516694158091248, 12.392299891053504, 
               59.01095186215066, -8.881784197001252e-10, 0, 0.02, 21.451669415809125, 207.5));


        left.setSegment(13, new Trajectory.Segment(1.7231197943749805, 13.572518928292787, 
               59.010951861964145, -9.325873406851315e-9, 0, 0.02, 21.72311979437498, 232.5));
        right.setSegment(13, new Trajectory.Segment(1.7231197943749805, 13.572518928292787, 
               59.010951861964145, -9.325873406851315e-9, 0, 0.02, 21.72311979437498, 207.5));


        left.setSegment(14, new Trajectory.Segment(2.0181745536856788, 14.752737965534912, 
               59.01095186210625, 7.105427357601002e-9, 0, 0.02, 22.01817455368568, 232.5));
        right.setSegment(14, new Trajectory.Segment(2.0181745536856788, 14.752737965534912, 
               59.01095186210625, 7.105427357601002e-9, 0, 0.02, 22.01817455368568, 207.5));


        left.setSegment(15, new Trajectory.Segment(2.3368336937412337, 15.932957002777748, 
               59.01095186214178, 1.7763568394002505e-9, 0, 0.02, 22.336833693741234, 232.5));
        right.setSegment(15, new Trajectory.Segment(2.3368336937412337, 15.932957002777748, 
               59.01095186214178, 1.7763568394002505e-9, 0, 0.02, 22.336833693741234, 207.5));


        left.setSegment(16, new Trajectory.Segment(2.679097214541642, 17.113176040020406, 
               59.0109518621329, -4.440892098500626e-10, 0, 0.02, 22.679097214541642, 232.5));
        right.setSegment(16, new Trajectory.Segment(2.679097214541642, 17.113176040020406, 
               59.0109518621329, -4.440892098500626e-10, 0, 0.02, 22.679097214541642, 207.5));


        left.setSegment(17, new Trajectory.Segment(3.044965116086935, 18.293395077264663, 
               59.010951862212835, 3.9968028886505635e-9, 0, 0.02, 23.044965116086935, 232.5));
        right.setSegment(17, new Trajectory.Segment(3.044965116086935, 18.293395077264663, 
               59.010951862212835, 3.9968028886505635e-9, 0, 0.02, 23.044965116086935, 207.5));


        left.setSegment(18, new Trajectory.Segment(3.4344373983773266, 19.473614114519577, 
               59.01095186274574, 2.6645352591003757e-8, 0, 0.02, 23.434437398377327, 232.5));
        right.setSegment(18, new Trajectory.Segment(3.4344373983773266, 19.473614114519577, 
               59.01095186274574, 2.6645352591003757e-8, 0, 0.02, 23.434437398377327, 207.5));


        left.setSegment(19, new Trajectory.Segment(3.847514061412589, 20.653833151763124, 
               59.01095186217731, -2.8421709430404007e-8, 0, 0.02, 23.84751406141259, 232.5));
        right.setSegment(19, new Trajectory.Segment(3.847514061412589, 20.653833151763124, 
               59.01095186217731, -2.8421709430404007e-8, 0, 0.02, 23.84751406141259, 207.5));


        left.setSegment(20, new Trajectory.Segment(4.2841951051927225, 21.83405218900667, 
               59.01095186217731, 0, 0, 0.02, 24.284195105192723, 232.5));
        right.setSegment(20, new Trajectory.Segment(4.2841951051927225, 21.83405218900667, 
               59.01095186217731, 0, 0, 0.02, 24.284195105192723, 207.5));


        left.setSegment(21, new Trajectory.Segment(4.74448052971773, 23.014271226250393, 
               59.01095186218619, 4.440892098500626e-10, 0, 0.02, 24.74448052971773, 232.5));
        right.setSegment(21, new Trajectory.Segment(4.74448052971773, 23.014271226250393, 
               59.01095186218619, 4.440892098500626e-10, 0, 0.02, 24.74448052971773, 207.5));


        left.setSegment(22, new Trajectory.Segment(5.228370334987609, 24.19449026349394, 
               59.01095186217731, -4.440892098500626e-10, 0, 0.02, 25.22837033498761, 232.5));
        right.setSegment(22, new Trajectory.Segment(5.228370334987609, 24.19449026349394, 
               59.01095186217731, -4.440892098500626e-10, 0, 0.02, 25.22837033498761, 207.5));


        left.setSegment(23, new Trajectory.Segment(5.735864521002359, 25.374709300737486, 
               59.01095186217731, 0, 0, 0.02, 25.73586452100236, 232.5));
        right.setSegment(23, new Trajectory.Segment(5.735864521002359, 25.374709300737486, 
               59.01095186217731, 0, 0, 0.02, 25.73586452100236, 207.5));


        left.setSegment(24, new Trajectory.Segment(6.266963087761614, 26.554928337962735, 
               59.010951861262484, -4.574118861455645e-8, 0, 0.02, 26.266963087761614, 232.5));
        right.setSegment(24, new Trajectory.Segment(6.266963087761614, 26.554928337962735, 
               59.010951861262484, -4.574118861455645e-8, 0, 0.02, 26.266963087761614, 207.5));


        left.setSegment(25, new Trajectory.Segment(6.821666035265338, 27.73514737518621, 
               59.010951861173666, -4.440892098500626e-9, 0, 0.02, 26.821666035265338, 232.5));
        right.setSegment(25, new Trajectory.Segment(6.821666035265338, 27.73514737518621, 
               59.010951861173666, -4.440892098500626e-9, 0, 0.02, 26.821666035265338, 207.5));


        left.setSegment(26, new Trajectory.Segment(7.3999733635139044, 28.915366412428334, 
               59.01095186210625, 4.6629367034256575e-8, 0, 0.02, 27.399973363513904, 232.5));
        right.setSegment(26, new Trajectory.Segment(7.3999733635139044, 28.915366412428334, 
               59.01095186210625, 4.6629367034256575e-8, 0, 0.02, 27.399973363513904, 207.5));


        left.setSegment(27, new Trajectory.Segment(8.001885072507307, 30.095585449670104, 
               59.01095186208849, -8.881784197001252e-10, 0, 0.02, 28.001885072507307, 232.5));
        right.setSegment(27, new Trajectory.Segment(8.001885072507307, 30.095585449670104, 
               59.01095186208849, -8.881784197001252e-10, 0, 0.02, 28.001885072507307, 207.5));


        left.setSegment(28, new Trajectory.Segment(8.627401162245555, 31.275804486912406, 
               59.010951862115135, 1.3322676295501878e-9, 0, 0.02, 28.627401162245555, 232.5));
        right.setSegment(28, new Trajectory.Segment(8.627401162245555, 31.275804486912406, 
               59.010951862115135, 1.3322676295501878e-9, 0, 0.02, 28.627401162245555, 207.5));


        left.setSegment(29, new Trajectory.Segment(9.276521632728635, 32.456023524154, 
               59.01095186207961, -1.7763568394002505e-9, 0, 0.02, 29.276521632728635, 232.5));
        right.setSegment(29, new Trajectory.Segment(9.276521632728635, 32.456023524154, 
               59.01095186207961, -1.7763568394002505e-9, 0, 0.02, 29.276521632728635, 207.5));


        left.setSegment(30, new Trajectory.Segment(9.949246483956557, 33.636242561396124, 
               59.01095186210625, 1.3322676295501878e-9, 0, 0.02, 29.949246483956557, 232.5));
        right.setSegment(30, new Trajectory.Segment(9.949246483956557, 33.636242561396124, 
               59.01095186210625, 1.3322676295501878e-9, 0, 0.02, 29.949246483956557, 207.5));


        left.setSegment(31, new Trajectory.Segment(10.645575715929319, 34.81646159863807, 
               59.01095186209737, -4.440892098500626e-10, 0, 0.02, 30.64557571592932, 232.5));
        right.setSegment(31, new Trajectory.Segment(10.645575715929319, 34.81646159863807, 
               59.01095186209737, -4.440892098500626e-10, 0, 0.02, 30.64557571592932, 207.5));


        left.setSegment(32, new Trajectory.Segment(11.365509328646922, 35.996680635880196, 
               59.01095186210625, 4.440892098500626e-10, 0, 0.02, 31.365509328646922, 232.5));
        right.setSegment(32, new Trajectory.Segment(11.365509328646922, 35.996680635880196, 
               59.01095186210625, 4.440892098500626e-10, 0, 0.02, 31.365509328646922, 207.5));


        left.setSegment(33, new Trajectory.Segment(12.109047322109362, 37.176899673121966, 
               59.01095186208849, -8.881784197001252e-10, 0, 0.02, 32.10904732210936, 232.5));
        right.setSegment(33, new Trajectory.Segment(12.109047322109362, 37.176899673121966, 
               59.01095186208849, -8.881784197001252e-10, 0, 0.02, 32.10904732210936, 207.5));


        left.setSegment(34, new Trajectory.Segment(12.876189696316644, 38.35711871036409, 
               59.01095186210625, 8.881784197001252e-10, 0, 0.02, 32.876189696316644, 232.5));
        right.setSegment(34, new Trajectory.Segment(12.876189696316644, 38.35711871036409, 
               59.01095186210625, 8.881784197001252e-10, 0, 0.02, 32.876189696316644, 207.5));


        left.setSegment(35, new Trajectory.Segment(13.66693645126876, 39.53733774760586, 
               59.01095186208849, -8.881784197001252e-10, 0, 0.02, 33.66693645126876, 232.5));
        right.setSegment(35, new Trajectory.Segment(13.66693645126876, 39.53733774760586, 
               59.01095186208849, -8.881784197001252e-10, 0, 0.02, 33.66693645126876, 207.5));


        left.setSegment(36, new Trajectory.Segment(14.48128758696572, 40.717556784847986, 
               59.01095186210625, 8.881784197001252e-10, 0, 0.02, 34.48128758696572, 232.5));
        right.setSegment(36, new Trajectory.Segment(14.48128758696572, 40.717556784847986, 
               59.01095186210625, 8.881784197001252e-10, 0, 0.02, 34.48128758696572, 207.5));


        left.setSegment(37, new Trajectory.Segment(15.319243103407516, 41.897775822089756, 
               59.01095186208849, -8.881784197001252e-10, 0, 0.02, 35.319243103407516, 232.5));
        right.setSegment(37, new Trajectory.Segment(15.319243103407516, 41.897775822089756, 
               59.01095186208849, -8.881784197001252e-10, 0, 0.02, 35.319243103407516, 207.5));


        left.setSegment(38, new Trajectory.Segment(16.180803000594153, 43.07799485933188, 
               59.01095186210625, 8.881784197001252e-10, 0, 0.02, 36.18080300059415, 232.5));
        right.setSegment(38, new Trajectory.Segment(16.180803000594153, 43.07799485933188, 
               59.01095186210625, 8.881784197001252e-10, 0, 0.02, 36.18080300059415, 207.5));


        left.setSegment(39, new Trajectory.Segment(17.065967278525633, 44.258213896574006, 
               59.01095186210625, 0, 0, 0.02, 37.06596727852563, 232.5));
        right.setSegment(39, new Trajectory.Segment(17.065967278525633, 44.258213896574006, 
               59.01095186210625, 0, 0, 0.02, 37.06596727852563, 207.5));


        left.setSegment(40, new Trajectory.Segment(17.97473593720194, 45.43843293381542, 
               59.010951862070726, -1.7763568394002505e-9, 0, 0.02, 37.97473593720194, 232.5));
        right.setSegment(40, new Trajectory.Segment(17.97473593720194, 45.43843293381542, 
               59.010951862070726, -1.7763568394002505e-9, 0, 0.02, 37.97473593720194, 207.5));


        left.setSegment(41, new Trajectory.Segment(18.9071089766231, 46.6186519710579, 
               59.01095186212402, 2.6645352591003757e-9, 0, 0.02, 38.9071089766231, 232.5));
        right.setSegment(41, new Trajectory.Segment(18.9071089766231, 46.6186519710579, 
               59.01095186212402, 2.6645352591003757e-9, 0, 0.02, 38.9071089766231, 207.5));


        left.setSegment(42, new Trajectory.Segment(19.863086396789093, 47.79887100829967, 
               59.01095186208849, -1.7763568394002505e-9, 0, 0.02, 39.86308639678909, 232.5));
        right.setSegment(42, new Trajectory.Segment(19.863086396789093, 47.79887100829967, 
               59.01095186208849, -1.7763568394002505e-9, 0, 0.02, 39.86308639678909, 207.5));


        left.setSegment(43, new Trajectory.Segment(20.840307759625446, 48.861068141817654, 
               53.10985667589918, -295.05475930946545, 0, 0.02, 40.840307759625446, 232.5));
        right.setSegment(43, new Trajectory.Segment(20.840307759625446, 48.861068141817654, 
               53.10985667589918, -295.05475930946545, 0, 0.02, 40.840307759625446, 207.5));


        left.setSegment(44, new Trajectory.Segment(21.83405218898318, 49.687221467886644, 
               41.30766630344951, -590.1095186224836, 0, 0.02, 41.83405218898318, 232.5));
        right.setSegment(44, new Trajectory.Segment(21.83405218898318, 49.687221467886644, 
               41.30766630344951, -590.1095186224836, 0, 0.02, 41.83405218898318, 207.5));


        left.setSegment(45, new Trajectory.Segment(22.83959880871334, 50.27733098650806, 
               29.50547593107089, -590.1095186189309, 0, 0.02, 42.83959880871334, 232.5));
        right.setSegment(45, new Trajectory.Segment(22.83959880871334, 50.27733098650806, 
               29.50547593107089, -590.1095186189309, 0, 0.02, 42.83959880871334, 207.5));


        left.setSegment(46, new Trajectory.Segment(23.852226742666943, 50.63139669768013, 
               17.703285558603454, -590.1095186233717, 0, 0.02, 43.85222674266694, 232.5));
        right.setSegment(46, new Trajectory.Segment(23.852226742666943, 50.63139669768013, 
               17.703285558603454, -590.1095186233717, 0, 0.02, 43.85222674266694, 207.5));


        left.setSegment(47, new Trajectory.Segment(24.865541994101036, 50.665762571704676, 
               1.7182937012272248, -799.2495928688114, 0, 0.02, 44.86554199410104, 232.5));
        right.setSegment(47, new Trajectory.Segment(24.865541994101036, 50.665762571704676, 
               1.7182937012272248, -799.2495928688114, 0, 0.02, 44.86554199410104, 207.5));


        left.setSegment(48, new Trajectory.Segment(25.87315056626381, 50.38042860813867, 
               -14.266698178300175, -799.24959397637, 0, 0.02, 45.87315056626381, 232.5));
        right.setSegment(48, new Trajectory.Segment(25.87315056626381, 50.38042860813867, 
               -14.266698178300175, -799.24959397637, 0, 0.02, 45.87315056626381, 207.5));


        left.setSegment(49, new Trajectory.Segment(26.870331583005452, 49.85905083708211, 
               -26.068888552828184, -590.1095187264004, 0, 0.02, 46.87033158300545, 232.5));
        right.setSegment(49, new Trajectory.Segment(26.870331583005452, 49.85905083708211, 
               -26.068888552828184, -590.1095187264004, 0, 0.02, 46.87033158300545, 207.5));


        left.setSegment(50, new Trajectory.Segment(27.852364168176962, 49.101629258575485, 
               -37.87107892533115, -590.1095186251481, 0, 0.02, 47.85236416817696, 232.5));
        right.setSegment(50, new Trajectory.Segment(27.852364168176962, 49.101629258575485, 
               -37.87107892533115, -590.1095186251481, 0, 0.02, 47.85236416817696, 207.5));


        left.setSegment(51, new Trajectory.Segment(28.814527445629366, 48.108163872620224, 
               -49.673269297763056, -590.1095186215954, 0, 0.02, 48.814527445629366, 232.5));
        right.setSegment(51, new Trajectory.Segment(28.814527445629366, 48.108163872620224, 
               -49.673269297763056, -590.1095186215954, 0, 0.02, 48.814527445629366, 207.5));


        left.setSegment(52, new Trajectory.Segment(29.753773659812474, 46.962310709155375, 
               -57.29265817324247, -380.9694437739708, 0, 0.02, 49.753773659812474, 232.5));
        right.setSegment(52, new Trajectory.Segment(29.753773659812474, 46.962310709155375, 
               -57.29265817324247, -380.9694437739708, 0, 0.02, 49.753773659812474, 207.5));


        left.setSegment(53, new Trajectory.Segment(30.669415493250604, 45.7820916719065, 
               -59.01095186244376, -85.91468446006445, 0, 0.02, 50.669415493250604, 232.5));
        right.setSegment(53, new Trajectory.Segment(30.669415493250604, 45.7820916719065, 
               -59.01095186244376, -85.91468446006445, 0, 0.02, 50.669415493250604, 207.5));


        left.setSegment(54, new Trajectory.Segment(31.56145294594377, 44.601872634658335, 
               -59.010951862408234, 1.7763568394002505e-9, 0, 0.02, 51.56145294594377, 232.5));
        right.setSegment(54, new Trajectory.Segment(31.56145294594377, 44.601872634658335, 
               -59.010951862408234, 1.7763568394002505e-9, 0, 0.02, 51.56145294594377, 207.5));


        left.setSegment(55, new Trajectory.Segment(32.42988601789196, 43.42165359740946, 
               -59.01095186244376, -1.7763568394002505e-9, 0, 0.02, 52.42988601789196, 232.5));
        right.setSegment(55, new Trajectory.Segment(32.42988601789196, 43.42165359740946, 
               -59.01095186244376, -1.7763568394002505e-9, 0, 0.02, 52.42988601789196, 207.5));


        left.setSegment(56, new Trajectory.Segment(33.27471470909519, 42.24143456016165, 
               -59.01095186239047, 2.6645352591003757e-9, 0, 0.02, 53.27471470909519, 232.5));
        right.setSegment(56, new Trajectory.Segment(33.27471470909519, 42.24143456016165, 
               -59.01095186239047, 2.6645352591003757e-9, 0, 0.02, 53.27471470909519, 207.5));


        left.setSegment(57, new Trajectory.Segment(34.09593901955344, 41.06121552291242, 
               -59.010951862461525, -3.552713678800501e-9, 0, 0.02, 54.09593901955344, 232.5));
        right.setSegment(57, new Trajectory.Segment(34.09593901955344, 41.06121552291242, 
               -59.010951862461525, -3.552713678800501e-9, 0, 0.02, 54.09593901955344, 207.5));


        left.setSegment(58, new Trajectory.Segment(34.893558949266726, 39.880996485664255, 
               -59.010951862408234, 2.6645352591003757e-9, 0, 0.02, 54.893558949266726, 232.5));
        right.setSegment(58, new Trajectory.Segment(34.893558949266726, 39.880996485664255, 
               -59.010951862408234, 2.6645352591003757e-9, 0, 0.02, 54.893558949266726, 207.5));


        left.setSegment(59, new Trajectory.Segment(35.66757449823504, 38.700777448415735, 
               -59.010951862426, -8.881784197001252e-10, 0, 0.02, 55.66757449823504, 232.5));
        right.setSegment(59, new Trajectory.Segment(35.66757449823504, 38.700777448415735, 
               -59.010951862426, -8.881784197001252e-10, 0, 0.02, 55.66757449823504, 207.5));


        left.setSegment(60, new Trajectory.Segment(36.41798566645838, 37.52055841116686, 
               -59.01095186244376, -8.881784197001252e-10, 0, 0.02, 56.41798566645838, 232.5));
        right.setSegment(60, new Trajectory.Segment(36.41798566645838, 37.52055841116686, 
               -59.01095186244376, -8.881784197001252e-10, 0, 0.02, 56.41798566645838, 207.5));


        left.setSegment(61, new Trajectory.Segment(37.14479245393676, 36.34033937391905, 
               -59.01095186239047, 2.6645352591003757e-9, 0, 0.02, 57.14479245393676, 232.5));
        right.setSegment(61, new Trajectory.Segment(37.14479245393676, 36.34033937391905, 
               -59.01095186239047, 2.6645352591003757e-9, 0, 0.02, 57.14479245393676, 207.5));


        left.setSegment(62, new Trajectory.Segment(37.847994860670156, 35.16012033666982, 
               -59.010951862461525, -3.552713678800501e-9, 0, 0.02, 57.847994860670156, 232.5));
        right.setSegment(62, new Trajectory.Segment(37.847994860670156, 35.16012033666982, 
               -59.010951862461525, -3.552713678800501e-9, 0, 0.02, 57.847994860670156, 207.5));


        left.setSegment(63, new Trajectory.Segment(38.52759288665858, 33.9799012994213, 
               -59.010951862426, 1.7763568394002505e-9, 0, 0.02, 58.52759288665858, 232.5));
        right.setSegment(63, new Trajectory.Segment(38.52759288665858, 33.9799012994213, 
               -59.010951862426, 1.7763568394002505e-9, 0, 0.02, 58.52759288665858, 207.5));


        left.setSegment(64, new Trajectory.Segment(39.18358653190204, 32.79968226217278, 
               -59.010951862426, 0, 0, 0.02, 59.18358653190204, 232.5));
        right.setSegment(64, new Trajectory.Segment(39.18358653190204, 32.79968226217278, 
               -59.010951862426, 0, 0, 0.02, 59.18358653190204, 207.5));


        left.setSegment(65, new Trajectory.Segment(39.81597579640053, 31.619463224924615, 
               -59.010951862408234, 8.881784197001252e-10, 0, 0.02, 59.81597579640053, 232.5));
        right.setSegment(65, new Trajectory.Segment(39.81597579640053, 31.619463224924615, 
               -59.010951862408234, 8.881784197001252e-10, 0, 0.02, 59.81597579640053, 207.5));


        left.setSegment(66, new Trajectory.Segment(40.42476068015405, 30.439244187676096, 
               -59.010951862426, -8.881784197001252e-10, 0, 0.02, 60.42476068015405, 232.5));
        right.setSegment(66, new Trajectory.Segment(40.42476068015405, 30.439244187676096, 
               -59.010951862426, -8.881784197001252e-10, 0, 0.02, 60.42476068015405, 207.5));


        left.setSegment(67, new Trajectory.Segment(41.0099411831626, 29.259025150427576, 
               -59.010951862426, 0, 0, 0.02, 61.0099411831626, 232.5));
        right.setSegment(67, new Trajectory.Segment(41.0099411831626, 29.259025150427576, 
               -59.010951862426, 0, 0, 0.02, 61.0099411831626, 207.5));


        left.setSegment(68, new Trajectory.Segment(41.57151730542618, 28.0788061131787, 
               -59.01095186244376, -8.881784197001252e-10, 0, 0.02, 61.57151730542618, 232.5));
        right.setSegment(68, new Trajectory.Segment(41.57151730542618, 28.0788061131787, 
               -59.01095186244376, -8.881784197001252e-10, 0, 0.02, 61.57151730542618, 207.5));


        left.setSegment(69, new Trajectory.Segment(42.10948904694479, 26.898587075930536, 
               -59.010951862408234, 1.7763568394002505e-9, 0, 0.02, 62.10948904694479, 232.5));
        right.setSegment(69, new Trajectory.Segment(42.10948904694479, 26.898587075930536, 
               -59.010951862408234, 1.7763568394002505e-9, 0, 0.02, 62.10948904694479, 207.5));


        left.setSegment(70, new Trajectory.Segment(42.62385640771843, 25.718368038682016, 
               -59.010951862426, -8.881784197001252e-10, 0, 0.02, 62.62385640771843, 232.5));
        right.setSegment(70, new Trajectory.Segment(42.62385640771843, 25.718368038682016, 
               -59.010951862426, -8.881784197001252e-10, 0, 0.02, 62.62385640771843, 207.5));


        left.setSegment(71, new Trajectory.Segment(43.11461938774709, 24.53814900143314, 
               -59.01095186244376, -8.881784197001252e-10, 0, 0.02, 63.11461938774709, 232.5));
        right.setSegment(71, new Trajectory.Segment(43.11461938774709, 24.53814900143314, 
               -59.01095186244376, -8.881784197001252e-10, 0, 0.02, 63.11461938774709, 207.5));


        left.setSegment(72, new Trajectory.Segment(43.58177798703078, 23.35792996418462, 
               -59.010951862426, 8.881784197001252e-10, 0, 0.02, 63.58177798703078, 232.5));
        right.setSegment(72, new Trajectory.Segment(43.58177798703078, 23.35792996418462, 
               -59.010951862426, 8.881784197001252e-10, 0, 0.02, 63.58177798703078, 207.5));


        left.setSegment(73, new Trajectory.Segment(44.02533220556951, 22.177710926936456, 
               -59.010951862408234, 8.881784197001252e-10, 0, 0.02, 64.02533220556951, 232.5));
        right.setSegment(73, new Trajectory.Segment(44.02533220556951, 22.177710926936456, 
               -59.010951862408234, 8.881784197001252e-10, 0, 0.02, 64.02533220556951, 207.5));


        left.setSegment(74, new Trajectory.Segment(44.44528204336328, 20.99749188968829, 
               -59.010951862408234, 0, 0, 0.02, 64.44528204336328, 232.5));
        right.setSegment(74, new Trajectory.Segment(44.44528204336328, 20.99749188968829, 
               -59.010951862408234, 0, 0, 0.02, 64.44528204336328, 207.5));


        left.setSegment(75, new Trajectory.Segment(44.84162750041206, 19.81727285243906, 
               -59.010951862461525, -2.6645352591003757e-9, 0, 0.02, 64.84162750041206, 232.5));
        right.setSegment(75, new Trajectory.Segment(44.84162750041206, 19.81727285243906, 
               -59.010951862461525, -2.6645352591003757e-9, 0, 0.02, 64.84162750041206, 207.5));


        left.setSegment(76, new Trajectory.Segment(45.21436857671587, 18.63705381519054, 
               -59.010951862426, 1.7763568394002505e-9, 0, 0.02, 65.21436857671587, 232.5));
        right.setSegment(76, new Trajectory.Segment(45.21436857671587, 18.63705381519054, 
               -59.010951862426, 1.7763568394002505e-9, 0, 0.02, 65.21436857671587, 207.5));


        left.setSegment(77, new Trajectory.Segment(45.56350527227471, 17.45683477794202, 
               -59.010951862426, 0, 0, 0.02, 65.56350527227471, 232.5));
        right.setSegment(77, new Trajectory.Segment(45.56350527227471, 17.45683477794202, 
               -59.010951862426, 0, 0, 0.02, 65.56350527227471, 207.5));


        left.setSegment(78, new Trajectory.Segment(45.889037587088595, 16.27661574069421, 
               -59.01095186239047, 1.7763568394002505e-9, 0, 0.02, 65.8890375870886, 232.5));
        right.setSegment(78, new Trajectory.Segment(45.889037587088595, 16.27661574069421, 
               -59.01095186239047, 1.7763568394002505e-9, 0, 0.02, 65.8890375870886, 207.5));


        left.setSegment(79, new Trajectory.Segment(46.190965521157494, 15.09639670344498, 
               -59.010951862461525, -3.552713678800501e-9, 0, 0.02, 66.1909655211575, 232.5));
        right.setSegment(79, new Trajectory.Segment(46.190965521157494, 15.09639670344498, 
               -59.010951862461525, -3.552713678800501e-9, 0, 0.02, 66.1909655211575, 207.5));


        left.setSegment(80, new Trajectory.Segment(46.46928907448142, 13.916177666196461, 
               -59.010951862426, 1.7763568394002505e-9, 0, 0.02, 66.46928907448142, 232.5));
        right.setSegment(80, new Trajectory.Segment(46.46928907448142, 13.916177666196461, 
               -59.010951862426, 1.7763568394002505e-9, 0, 0.02, 66.46928907448142, 207.5));


        left.setSegment(81, new Trajectory.Segment(46.72400824706038, 12.735958628947941, 
               -59.010951862426, 0, 0, 0.02, 66.72400824706038, 232.5));
        right.setSegment(81, new Trajectory.Segment(46.72400824706038, 12.735958628947941, 
               -59.010951862426, 0, 0, 0.02, 66.72400824706038, 207.5));


        left.setSegment(82, new Trajectory.Segment(46.955123038894385, 11.555739591700132, 
               -59.01095186239047, 1.7763568394002505e-9, 0, 0.02, 66.95512303889438, 232.5));
        right.setSegment(82, new Trajectory.Segment(46.955123038894385, 11.555739591700132, 
               -59.01095186239047, 1.7763568394002505e-9, 0, 0.02, 66.95512303889438, 207.5));


        left.setSegment(83, new Trajectory.Segment(47.16263344998342, 10.375520554451612, 
               -59.010951862426, -1.7763568394002505e-9, 0, 0.02, 67.16263344998342, 232.5));
        right.setSegment(83, new Trajectory.Segment(47.16263344998342, 10.375520554451612, 
               -59.010951862426, -1.7763568394002505e-9, 0, 0.02, 67.16263344998342, 207.5));


        left.setSegment(84, new Trajectory.Segment(47.346539480327465, 9.195301517202381, 
               -59.010951862461525, -1.7763568394002505e-9, 0, 0.02, 67.34653948032746, 232.5));
        right.setSegment(84, new Trajectory.Segment(47.346539480327465, 9.195301517202381, 
               -59.010951862461525, -1.7763568394002505e-9, 0, 0.02, 67.34653948032746, 207.5));


        left.setSegment(85, new Trajectory.Segment(47.50684112992654, 8.015082479953861, 
               -59.010951862426, 1.7763568394002505e-9, 0, 0.02, 67.50684112992654, 232.5));
        right.setSegment(85, new Trajectory.Segment(47.50684112992654, 8.015082479953861, 
               -59.010951862426, 1.7763568394002505e-9, 0, 0.02, 67.50684112992654, 207.5));


        left.setSegment(86, new Trajectory.Segment(47.64353839878066, 6.834863442706052, 
               -59.01095186239047, 1.7763568394002505e-9, 0, 0.02, 67.64353839878066, 232.5));
        right.setSegment(86, new Trajectory.Segment(47.64353839878066, 6.834863442706052, 
               -59.01095186239047, 1.7763568394002505e-9, 0, 0.02, 67.64353839878066, 207.5));


        left.setSegment(87, new Trajectory.Segment(47.756631286889785, 5.654644405456111, 
               -59.01095186249705, -5.329070518200751e-9, 0, 0.02, 67.75663128688979, 232.5));
        right.setSegment(87, new Trajectory.Segment(47.756631286889785, 5.654644405456111, 
               -59.01095186249705, -5.329070518200751e-9, 0, 0.02, 67.75663128688979, 207.5));


        left.setSegment(88, new Trajectory.Segment(47.846119794253966, 4.474425368209012, 
               -59.01095186235494, 7.105427357601002e-9, 0, 0.02, 67.84611979425397, 232.5));
        right.setSegment(88, new Trajectory.Segment(47.846119794253966, 4.474425368209012, 
               -59.01095186235494, 7.105427357601002e-9, 0, 0.02, 67.84611979425397, 207.5));


        left.setSegment(89, new Trajectory.Segment(47.91200392087316, 3.2942063309597813, 
               -59.010951862461525, -5.329070518200751e-9, 0, 0.02, 67.91200392087316, 232.5));
        right.setSegment(89, new Trajectory.Segment(47.91200392087316, 3.2942063309597813, 
               -59.010951862461525, -5.329070518200751e-9, 0, 0.02, 67.91200392087316, 207.5));


        left.setSegment(90, new Trajectory.Segment(47.95595678734621, 2.197643323652443, 
               -54.82815036536692, 209.14007485473007, 0, 0.02, 67.95595678734621, 232.5));
        right.setSegment(90, new Trajectory.Segment(47.95595678734621, 2.197643323652443, 
               -54.82815036536692, 209.14007485473007, 0, 0.02, 67.95595678734621, 207.5));


        left.setSegment(91, new Trajectory.Segment(47.982011952346426, 1.3027582500107826, 
               -44.74425368208301, 504.1948341641955, 0, 0.02, 67.98201195234643, 232.5));
        right.setSegment(91, new Trajectory.Segment(47.982011952346426, 1.3027582500107826, 
               -44.74425368208301, 504.1948341641955, 0, 0.02, 67.98201195234643, 207.5));


        left.setSegment(92, new Trajectory.Segment(47.9948902920228, 0.6439169838188263, 
               -32.94206330959781, 590.10951862426, 0, 0.02, 67.9948902920228, 232.5));
        right.setSegment(92, new Trajectory.Segment(47.9948902920228, 0.6439169838188263, 
               -32.94206330959781, 590.10951862426, 0, 0.02, 67.9948902920228, 207.5));


        left.setSegment(93, new Trajectory.Segment(47.999312682524334, 0.22111952507657406, 
               -21.139872937112614, 590.10951862426, 0, 0.02, 67.99931268252433, 232.5));
        right.setSegment(93, new Trajectory.Segment(47.999312682524334, 0.22111952507657406, 
               -21.139872937112614, 590.10951862426, 0, 0.02, 67.99931268252433, 207.5));


        left.setSegment(94, new Trajectory.Segment(48, 0.03436587378331524, 
               -9.337682564662941, 590.1095186224836, 0, 0.02, 68, 232.5));
        right.setSegment(94, new Trajectory.Segment(48, 0.03436587378331524, 
               -9.337682564662941, 590.1095186224836, 0, 0.02, 68, 207.5));


        left.setSegment(95, new Trajectory.Segment(48, 0, 
               -1.7182936891657619, 380.969443774859, 0, 0.02, 68, 232.5));
        right.setSegment(95, new Trajectory.Segment(48, 0, 
               -1.7182936891657619, 380.969443774859, 0, 0.02, 68, 207.5));


    }


    public boolean isReversed() {
        return true; 
    }
  
	// WAYPOINT_DATA: [{"position":{"x":20,"y":220},"theta":0,"comment":""},{"position":{"x":68,"y":220},"theta":0,"comment":""}]
	// IS_REVERSED: true
	// FILE_NAME: Backup
  // DT: 0.02
  // MAX_VEL: 80
  // MAX_ACC: 60
  // MAX_JERK: 600
  // WHEEL_BASE: 25
  // PACKAGE: org.usfirst.frc862.glitch.paths
  // PARENT: org.usfirst.frc862.util.DynamicPathCommand
}