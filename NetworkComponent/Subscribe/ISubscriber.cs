using NetworkComponent.Transferable;

namespace NetworkComponent.Subscribe
{
    public interface ISubscriber
    {
        void Update(ITransferable notification);
    }
}