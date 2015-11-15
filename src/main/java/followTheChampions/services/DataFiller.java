package followTheChampions.services;

import followTheChampions.dao.*;
import followTheChampions.models.*;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;

@Service
@Transactional
public class DataFiller {
    @Autowired
    CompetitionRepository competitionRepository;

    @Autowired
    FavouritedMatchRepository favouritedMatchRepository;

    @Autowired
    FavouritedTeamRepository favouritedTeamRepository;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    MatchEventRepository matchEventRepository;

    @Autowired
    RegisteredDeviceRepository registeredDeviceRepository;

    @Autowired
    StandingRepository standingRepository;

    @Autowired
    TeamRepository teamRepository;

    private final static Logger logger = LoggerFactory
            .getLogger(DataFiller.class);

    public void initiateData(){

        logger.info("Rozpoczynam zasilanie bazy danych");

        LinkedList<Competition> competitions = new LinkedList<>();
        LinkedList<FavouritedMatch> favouritedMatches = new LinkedList<>();
        LinkedList<FavouritedTeam> favouritedTeams = new LinkedList<>();
        LinkedList<Match> matches = new LinkedList<>();
        LinkedList<MatchEvent> matchEvents = new LinkedList<>();
        LinkedList<RegisteredDevice> registeredDevices = new LinkedList<>();
        LinkedList<Standing> standings = new LinkedList<>();
        LinkedList<Team> teams = new LinkedList<>();

        //////////////////////////////////////////////////////////////////////////

        registeredDevices.add(0,new RegisteredDevice());
        registeredDevices.get(0).setRegistrationDate(DateTime.now().toDate());
        registeredDevices.get(0).setIsActive(Boolean.TRUE);
        registeredDevices.get(0).setDeviceToken("demoToken");

        logger.info(registeredDevices.get(0).getRegistrationDate().toString());

        for(RegisteredDevice registeredDevice : registeredDevices){
            registeredDeviceRepository.save(registeredDevice);
        }

        ////////////////////////////////////////////////////////////////

        competitions.add(0,new Competition());
        competitions.get(0).setName("England");
        competitions.get(0).setRegion("Premier League");

        for(Competition competition : competitions){
            competitionRepository.save(competition);
        }

        /////////////////////////////////////////////////////////////////

        teams.add(0,new Team());
        teams.get(0).setName("Arsenal FC");

        teams.add(1,new Team());
        teams.get(1).setName("Chelsea FC");

        teams.add(2,new Team());
        teams.get(2).setName("Liverpool");

        for(Team team : teams){
            teamRepository.save(team);
        }
        //////////////////////////////////////////////////////////////////

        matches.add(0,new Match());
        matches.get(0).setCompetition( competitionRepository.getById(1L) );
        matches.get(0).setMatchDate(DateTime.now().toDate());
        matches.get(0).setStatus("END");
        matches.get(0).setTime("90:00");
        matches.get(0).setIsCommentaryAvailable(Boolean.TRUE);
        matches.get(0).setLocalTeam( teamRepository.getById(1L) );
        matches.get(0).setLocalTeamScore("3");
        matches.get(0).setVisitorTeam( teamRepository.getById(2L) );
        matches.get(0).setVisitorTeamScore("2");
        matches.get(0).setMatchHtScore("3:2");

        matches.add(1,new Match());
        matches.get(1).setCompetition( competitionRepository.getById(1L) );
        matches.get(1).setMatchDate(DateTime.now().toDate());
        matches.get(1).setStatus("END");
        matches.get(1).setTime("90:00");
        matches.get(1).setIsCommentaryAvailable(Boolean.TRUE);
        matches.get(1).setLocalTeam( teamRepository.getById(2L) );
        matches.get(1).setLocalTeamScore("0");
        matches.get(1).setVisitorTeam( teamRepository.getById(3L) );
        matches.get(1).setVisitorTeamScore("0");
        matches.get(1).setMatchHtScore("0:0");

        matches.add(2,new Match());
        matches.get(2).setCompetition( competitionRepository.getById(1L) );
        matches.get(2).setMatchDate(DateTime.now().toDate());
        matches.get(2).setStatus("END");
        matches.get(2).setTime("90:00");
        matches.get(2).setIsCommentaryAvailable(Boolean.TRUE);
        matches.get(2).setLocalTeam( teamRepository.getById(3L) );
        matches.get(2).setLocalTeamScore("1");
        matches.get(2).setVisitorTeam( teamRepository.getById(1L) );
        matches.get(2).setVisitorTeamScore("2");
        matches.get(2).setMatchHtScore("1:2");

        for(Match match : matches){
            matchRepository.save(match);
        }

        //////////////////////////////////////////////////////////////////////////

        favouritedTeams.add(0,new FavouritedTeam());
        favouritedTeams.get(0).setRegisteredDevice( registeredDeviceRepository.getById(1L) );
        favouritedTeams.get(0).setTeam( teamRepository.getById(3L));

        for(FavouritedTeam favouritedTeam : favouritedTeams){
            favouritedTeamRepository.save(favouritedTeam);
        }

        /////////////////////////////////////////////////////////////////////////

        favouritedMatches.add(0,new FavouritedMatch());
        favouritedMatches.get(0).setRegisteredDevice( registeredDeviceRepository.getById(1L) );
        favouritedMatches.get(0).setMatch( matchRepository.getById(1L) );

        favouritedMatches.add(1,new FavouritedMatch());
        favouritedMatches.get(1).setRegisteredDevice( registeredDeviceRepository.getById(1L) );
        favouritedMatches.get(1).setMatch( matchRepository.getById(1L) );

        for(FavouritedMatch favouritedMatch : favouritedMatches){
            favouritedMatchRepository.save(favouritedMatch);
        }


        logger.info("Baza danych zainicjowana");
    }
}
