using ModelComponent.Convertor;
using ModelComponent.Domain;
using ModelComponent.Entity;

namespace ModelComponent.Repository
{
    public class RepositoryFactory
    {
        public static IRepositoryRelation<T, TD, E, ED, TE> GetRelation<T, TD, E, ED, TE>(string database, RepositoryType type)
            where ED : IIdableEntity, new()
            where E : Idable<int>, new()
            where TD : IIdableEntity, new()
            where T : Idable<int>, new()
        {
            switch (type)
            {
                default:
                    return (IRepositoryRelation<T, TD, E, ED, TE>)
                        new RepositoryRelation<Player, PlayerEntity, Event, EventEntity, PlayerEventEntity>(
                            database,
                            ConvertorFactory.GetInstance<Player, PlayerEntity>(ConvertorType.Player),
                            ConvertorFactory.GetInstance<Event, EventEntity>(ConvertorType.Event),
                            ConvertorFactory.GetRelation<
                                Player,
                                PlayerEntity,
                                Event,
                                EventEntity,
                                PlayerEventEntity>(ConvertorType.PlayerEvent)
                        );
            }
        }

        public static IRepositoryEntity<T, E> GetInstance<T, E>(string database, RepositoryType type)
            where E : IIdableEntity, new()
        {
            switch (type)
            {
                case RepositoryType.Event:
                    return (IRepositoryEntity<T, E>)
                        new RepositoryEntity<Event, EventEntity>(database,
                            ConvertorFactory.GetInstance<Event, EventEntity>(ConvertorType.Event));
                case RepositoryType.Player:
                    return (IRepositoryEntity<T, E>)
                        new RepositoryEntity<Player, PlayerEntity>(database,
                            ConvertorFactory.GetInstance<Player, PlayerEntity>(ConvertorType.Player));
                case RepositoryType.User:
                    return (IRepositoryEntity<T, E>)
                        new RepositoryEntity<User, UserEntity>(database,
                            ConvertorFactory.GetInstance<User, UserEntity>(ConvertorType.User));
                default:
                    return (IRepositoryEntity<T, E>)
                        new RepositoryEntity<Player, PlayerEntity>(database,
                            ConvertorFactory.GetInstance<Player, PlayerEntity>(ConvertorType.Player));
            }
        }
    }
}