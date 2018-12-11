namespace ModelComponent.Convertor
{
    public class ConvertorFactory
    {
        public static IConvertorRelation<T, TD, E, ED, TE> GetRelation<T, TD, E, ED, TE>(ConvertorType type)
        {
            switch (type)
            {
                case ConvertorType.PlayerEvent:
                    return (IConvertorRelation<T, TD, E, ED, TE>)
                        new ConvertorPlayerEventRelation();
                default:
                    return (IConvertorRelation<T, TD, E, ED, TE>)
                        new ConvertorPlayerEventRelation();
            }
        }

        public static IConvertorEntity<T, E> GetInstance<T, E>(ConvertorType type)
        {
            switch (type)
            {
                case ConvertorType.Player:
                    return (IConvertorEntity<T, E>)
                        new ConvertorPlayerEntity();
                case ConvertorType.Event:
                    return (IConvertorEntity<T, E>)
                        new ConvertorEventEntity();
                case ConvertorType.User:
                    return (IConvertorEntity<T, E>)
                        new ConvertorUserEntity();
                default:
                    return (IConvertorEntity<T, E>)
                        new ConvertorPlayerEntity();
            }
        }
    }
}