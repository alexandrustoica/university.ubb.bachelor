using System.Collections.Generic;
using ModelComponent.Domain;
using ModelComponent.Entity;
using ModelComponent.Repository;

namespace ModelComponent.Model
{
    public class ModelPlayer : Model<Player, PlayerEntity>
    {
        private readonly IRepositoryEntity<Event, EventEntity> _repositoryEvent;
        private readonly IRepositoryRelation<Player, PlayerEntity, Event, EventEntity, PlayerEventEntity>
            _repositoryRelation;

        public ModelPlayer(string database) : base(database)
        {
            Repository = RepositoryFactory.GetInstance<Player, PlayerEntity>(PreferredDatabaseSource, RepositoryType.Player);
            _repositoryEvent = RepositoryFactory.GetInstance<Event, EventEntity>(PreferredDatabaseSource,
                RepositoryType.Event);
            _repositoryRelation =
                RepositoryFactory.GetRelation<Player, PlayerEntity, Event, EventEntity, PlayerEventEntity>(
                    PreferredDatabaseSource, RepositoryType.PlayerEvent);
        }

        public ModelPlayer()
        {
            Repository = RepositoryFactory.GetInstance<Player, PlayerEntity>(PreferredDatabaseSource, RepositoryType.Player);
            _repositoryEvent = RepositoryFactory.GetInstance<Event, EventEntity>(PreferredDatabaseSource,
                RepositoryType.Event);
            _repositoryRelation =
                RepositoryFactory.GetRelation<Player, PlayerEntity, Event, EventEntity, PlayerEventEntity>(
                    PreferredDatabaseSource, RepositoryType.PlayerEvent);
        }

        public void AddEventToPlayer(int idEvent, int idPlayer)
        {
            var item = _repositoryEvent.FindElementById(idEvent);
            var element = Repository.FindElementById(idPlayer);
            if (item != null && element != null)
            {
                _repositoryRelation.Add(element, item);
            }
        }

        public void DeleteEventFromPlayer(int idEvent, int idPlayer)
        {
            var item = _repositoryEvent.FindElementById(idEvent);
            var element = Repository.FindElementById(idPlayer);
            if (item != null && element != null)
            {
                _repositoryRelation.Delete(element, item);
            }
        }

        public List<Event> GetAllPlayerEvents(int idPlayer)
        {
            var element = Repository.FindElementById(idPlayer);
            return element != null ? _repositoryRelation.GetAllE(element) : new List<Event>();
        }
    }
}