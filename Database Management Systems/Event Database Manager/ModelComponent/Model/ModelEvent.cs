using System.Collections.Generic;
using ModelComponent.Domain;
using ModelComponent.Entity;
using ModelComponent.Repository;

namespace ModelComponent.Model
{
    public class ModelEvent : Model<Event, EventEntity>
    {
        private readonly IRepositoryEntity<Player, PlayerEntity> _repositoryPlayer;
        private readonly IRepositoryRelation<Player, PlayerEntity, Event, EventEntity, PlayerEventEntity>
            _repositoryRelation;

        public ModelEvent(string database) : base(database)
        {
            Repository = RepositoryFactory.GetInstance<Event, EventEntity>(PreferredDatabaseSource, RepositoryType.Event);
            _repositoryPlayer = RepositoryFactory.GetInstance<Player, PlayerEntity>(PreferredDatabaseSource,
                RepositoryType.Player);
            _repositoryRelation =
                RepositoryFactory.GetRelation<Player, PlayerEntity, Event, EventEntity, PlayerEventEntity>(
                    PreferredDatabaseSource, RepositoryType.PlayerEvent);
        }

        public ModelEvent()
        {
            Repository = RepositoryFactory.GetInstance<Event, EventEntity>(PreferredDatabaseSource, RepositoryType.Event);
            _repositoryPlayer = RepositoryFactory.GetInstance<Player, PlayerEntity>(PreferredDatabaseSource,
                RepositoryType.Player);
            _repositoryRelation =
                RepositoryFactory.GetRelation<Player, PlayerEntity, Event, EventEntity, PlayerEventEntity>(
                    PreferredDatabaseSource, RepositoryType.PlayerEvent);
        }

        public void AddEventToPlayer(int idEvent, int idPlayer)
        {
            var item = _repositoryPlayer.FindElementById(idPlayer);
            var element = Repository.FindElementById(idEvent);
            if (item != null && element != null)
            {
                _repositoryRelation.Add(item, element);
            }
        }

        public void DeleteEventFromPlayer(int idEvent, int idPlayer)
        {
            var item = _repositoryPlayer.FindElementById(idPlayer);
            var element = Repository.FindElementById(idEvent);
            if (item != null && element != null)
            {
                _repositoryRelation.Delete(item, element);
            }
        }

        public List<Player> GetAllEventPlayers(int idEvent)
        {
            var element = Repository.FindElementById(idEvent);
            return element != null ? _repositoryRelation.GetAllT(element) : new List<Player>();
        }
    }
}