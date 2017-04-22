namespace NetworkComponent.Subscribe
{
    public interface IConnectionObserver: ISubscriber
    {
        void UpdateSystem(object response);
    }
}