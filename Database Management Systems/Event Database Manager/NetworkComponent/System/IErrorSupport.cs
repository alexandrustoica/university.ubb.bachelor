namespace NetworkComponent.System
{
    public interface IErrorSupport
    {
        void SetError(int errorCode, string message);
        int GetErrorCode();
        string GetErrorMessage();
    }
}