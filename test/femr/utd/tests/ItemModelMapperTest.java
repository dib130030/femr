package femr.utd.tests;


import femr.common.ItemModelMapper;
import femr.common.models.MissionTripItem;
import femr.common.models.TabFieldItem;
import femr.data.models.core.IMissionCity;
import femr.data.models.core.IMissionCountry;
import femr.data.models.core.IMissionTeam;
import femr.data.models.core.IMissionTrip;
import femr.data.models.mysql.MissionCity;
import femr.data.models.mysql.MissionCountry;
import femr.data.models.mysql.MissionTeam;
import femr.data.models.mysql.MissionTrip;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by Arie Jian on 11/8/16.
 */
public class ItemModelMapperTest extends BaseTest {
    private final ItemModelMapper itemModelMapper = new ItemModelMapper();

    @Before
    public void setup() {
    }

    @Test
    public void testCreateTabFieldItem() {
        // Arrange
        final String tabFieldItemName = "tabFieldItemName";
        final String tabFieldItemType = "tabFieldItemType";
        final String tabFieldItemSize = "tabFieldItemSize";
        final Integer tabFieldItemOrder = 1;
        final String tabFieldItemPlaceholder = "tabFieldItemPlaceholder";
        final String tabFieldItemValue = "tabFieldItemValue";
        final String tabFieldItemChiefComplaint = "tabFieldItemChiefComplaint";
        final Boolean tabFieldItemIsCustom = true;
        final String tabFieldItemUsername = "tabFieldItemUsername";

        // Act
        final TabFieldItem item = itemModelMapper.createTabFieldItem(tabFieldItemName, tabFieldItemType, tabFieldItemSize,
                tabFieldItemOrder, tabFieldItemPlaceholder, tabFieldItemValue, tabFieldItemChiefComplaint,
                tabFieldItemIsCustom, tabFieldItemUsername);

        // Assert
        assertEquals(tabFieldItemName, item.getName());
        assertEquals(tabFieldItemType, item.getType());
        assertEquals(tabFieldItemSize, item.getSize());
        assertEquals(tabFieldItemOrder, item.getOrder());
        assertEquals(tabFieldItemPlaceholder, item.getPlaceholder());
        assertEquals(tabFieldItemValue, item.getValue());
        assertEquals(tabFieldItemChiefComplaint, item.getChiefComplaint());
        assertEquals(tabFieldItemIsCustom, item.getIsCustom());
        assertEquals(tabFieldItemUsername, item.getUserName());
    }

    @Test
    public void testCreateTabFieldItemFactory() {
        // Arrange
        final String tabFieldItemName = "tabFieldItemName";
        final String tabFieldItemType = "tabFieldItemType";
        final String tabFieldItemSize = "tabFieldItemSize";
        final Integer tabFieldItemOrder = 1;
        final String tabFieldItemPlaceholder = "tabFieldItemPlaceholder";
        final String tabFieldItemValue = "tabFieldItemValue";
        final String tabFieldItemChiefComplaint = "tabFieldItemChiefComplaint";
        final Boolean tabFieldItemIsCustom = true;
        final String tabFieldItemUsername = "tabFieldItemUsername";

        // Act
        ItemModelMapper.TabFieldItemBuilder builder = itemModelMapper.new TabFieldItemBuilder();
        builder.setType(tabFieldItemType);
        builder.setSize(tabFieldItemSize);
        builder.setOrder(tabFieldItemOrder);
        builder.setPlaceholder(tabFieldItemPlaceholder);
        builder.setValue(tabFieldItemValue);
        builder.setChiefComplaint(tabFieldItemChiefComplaint);
        builder.setUsername(tabFieldItemUsername);
        final TabFieldItem item = builder.Create(tabFieldItemName, tabFieldItemIsCustom);

        // Assert
        assertEquals(tabFieldItemName, item.getName());
        assertEquals(tabFieldItemType, item.getType());
        assertEquals(tabFieldItemSize, item.getSize());
        assertEquals(tabFieldItemOrder, item.getOrder());
        assertEquals(tabFieldItemPlaceholder, item.getPlaceholder());
        assertEquals(tabFieldItemValue, item.getValue());
        assertEquals(tabFieldItemChiefComplaint, item.getChiefComplaint());
        assertEquals(tabFieldItemIsCustom, item.getIsCustom());
        assertEquals(tabFieldItemUsername, item.getUserName());
    }

    @Test
    public void testCreateMissionTripItem() {
        // Arrange
        final String missionTeamName = "Group 4";
        final String missionCityName = "Taipei";
        final String missionCountryName = "Taiwan";
        final GregorianCalendar missionEndDate = new GregorianCalendar(2016, 11, 20);
        final GregorianCalendar missionStartDate = new GregorianCalendar(2016, 10, 10);
        final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        final String friendlyTripStartDate = formatter.format(missionStartDate.getTime()).toString();
        final String friendlyTripEndDate = formatter.format(missionEndDate.getTime()).toString();

        final IMissionTeam missionTeam = new MissionTeam();
        missionTeam.setName(missionTeamName);

        final IMissionCountry missionCountry = new MissionCountry();
        missionCountry.setName(missionCountryName);

        final IMissionCity missionCity = new MissionCity();
        missionCity.setName(missionCityName);
        missionCity.setMissionCountry(missionCountry);

        final IMissionTrip missionTrip = new MissionTrip();
        missionTrip.setMissionTeam(missionTeam);
        missionTrip.setMissionCity(missionCity);
        missionTrip.setStartDate(missionStartDate.getTime());
        missionTrip.setEndDate(missionEndDate.getTime());

        // Act
        System.out.println("[info] Testing createMissionTripItem() method");
        final MissionTripItem missionTripItem = itemModelMapper.createMissionTripItem(missionTrip);

        // Assert
        assertEquals(missionTeamName, missionTripItem.getTeamName());
        assertEquals(missionCityName, missionTripItem.getTripCity());
        assertEquals(missionCountryName,missionTripItem.getTripCountry());
        assertEquals(missionStartDate.getTime(), missionTripItem.getTripStartDate());
        assertEquals(missionEndDate.getTime(), missionTripItem.getTripEndDate());
        assertEquals(friendlyTripStartDate,missionTripItem.getFriendlyTripStartDate());
        assertEquals(friendlyTripEndDate,missionTripItem.getFriendlyTripEndDate());
    }

    @After
    public void teardown() {
    }
}
