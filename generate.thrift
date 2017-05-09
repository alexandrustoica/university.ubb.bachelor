namespace java event.manager.service
namespace csharp EventManager.Service

enum Notification {
    UPDATE_ALL
}

struct UserData {
    1: optional i32 id;
    2: required string username;
    3: required string password;
}

struct PlayerData {
    1: optional i32 id;
    2: required string name;
    3: required i32 age;
}

struct EventData {
    1: optional i32 id;
    2: required i32 distance;
    3: required string style;
}

service Subscriber {
    oneway void update(1: Notification notification);
}

service Subsciption {
    string sendMessage(1: string message);
    UserData login(1: string username, 2: string password);
    UserData signUp(1: string username, 2: string password, 3: string confirm);
    list<PlayerData> getPlayers();
    list<EventData> getEvents();
    list<EventData> getEventsFromPlayer(1: PlayerData player);
    list<PlayerData> getPlayersFromEvent(1: EventData event)
    i32 addPlayer(1: PlayerData player, 2: list<EventData> events);
    void subscribe(1: string hostname, 2: i32 port);
    void unsubscribe(1: string hostname, 2: i32 port);
}
