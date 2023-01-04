import com.github.vitalliuss.models.ClassificationLevel;
import com.github.vitalliuss.models.ExperimentalType;
import com.github.vitalliuss.models.MilitaryType;
import com.github.vitalliuss.models.planes.ExperimentalPlane;
import com.github.vitalliuss.models.planes.MilitaryPlane;
import com.github.vitalliuss.models.planes.PassengerPlane;
import com.github.vitalliuss.models.planes.Plane;
import com.github.vitalliuss.services.Airport;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AirportTest {
    private static List<Plane> planes = Arrays.asList(
            new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
            new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
            new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
            new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
            new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
            new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
            new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
            new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
            new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER),
            new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER),
            new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT),
            new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalType.HIGH_ALTITUDE, ClassificationLevel.SECRET),
            new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalType.VTOL, ClassificationLevel.TOP_SECRET)
    );
    private static Airport airport = new Airport(planes);

    @Test
    public void testGetTransportMilitaryPlanes() {
        List<Plane> actualTransportMilitaryPlanes = airport.
                getMilitaryPlanes().
                stream().
                filter(plane -> ((MilitaryPlane) plane).getMilitaryType() == MilitaryType.TRANSPORT).
                collect(Collectors.toList());
        Assert.assertEquals(actualTransportMilitaryPlanes, airport.getTransportMilitaryPlanes());
    }

    @Test
    public void testGetPassengerPlaneWithMaxCapacity() {
        PassengerPlane planeWithMaxPassengerCapacity = airport.
                getPassengerPlanes().
                stream().
                map(plane -> ((PassengerPlane) plane)).max(Comparator.comparingInt(PassengerPlane::getPassengerCapacity)).
                get();
        Assert.assertEquals(planeWithMaxPassengerCapacity, airport.getPassengerPlaneWithMaxPassengerCapacity());
    }

    @Test
    public void testSortByMaxLoadCapacity() {
        List<Plane> notSortedPlanes = airport.getPlanes();
        airport.sortByMaxLoadCapacity();
        notSortedPlanes.sort(Comparator.comparingInt(Plane::getMaxLoadCapacity));
        Assert.assertEquals(notSortedPlanes, airport.getPlanes());
    }

    @Test
    public void testGetBomberMilitaryPlanes() {
        List<Plane> bomberMilitaryPlanes = airport.
                getMilitaryPlanes().
                stream().
                filter(plane -> ((MilitaryPlane) plane).getMilitaryType() == MilitaryType.BOMBER).
                collect(Collectors.toList());
        Assert.assertEquals(bomberMilitaryPlanes, airport.getBomberMilitaryPlanes());
    }

    @Test
    public void testExperimentalPlanesHasNotClassificationLevelUnclassified() {
        List<Plane> unclassifiedExperimentalPlanes = airport.
                getExperimentalPlanes().
                stream().
                filter(plane -> ((ExperimentalPlane) plane).getClassificationLevel() == ClassificationLevel.UNCLASSIFIED).
                collect(Collectors.toList());
        Assert.assertTrue(unclassifiedExperimentalPlanes.isEmpty());
    }
}
