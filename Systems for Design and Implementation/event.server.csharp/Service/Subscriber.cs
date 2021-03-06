/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using System.IO;
using Thrift;
using Thrift.Collections;
using System.Runtime.Serialization;
using Thrift.Protocol;
using Thrift.Transport;

namespace EventManager.Service
{
  public partial class Subscriber {
    public interface ISync {
      void update(Notification notification);
    }

    public interface Iface : ISync {
      #if SILVERLIGHT
      IAsyncResult Begin_update(AsyncCallback callback, object state, Notification notification);
      void End_update(IAsyncResult asyncResult);
      #endif
    }

    public class Client : IDisposable, Iface {
      public Client(TProtocol prot) : this(prot, prot)
      {
      }

      public Client(TProtocol iprot, TProtocol oprot)
      {
        iprot_ = iprot;
        oprot_ = oprot;
      }

      protected TProtocol iprot_;
      protected TProtocol oprot_;
      protected int seqid_;

      public TProtocol InputProtocol
      {
        get { return iprot_; }
      }
      public TProtocol OutputProtocol
      {
        get { return oprot_; }
      }


      #region " IDisposable Support "
      private bool _IsDisposed;

      // IDisposable
      public void Dispose()
      {
        Dispose(true);
      }
      

      protected virtual void Dispose(bool disposing)
      {
        if (!_IsDisposed)
        {
          if (disposing)
          {
            if (iprot_ != null)
            {
              ((IDisposable)iprot_).Dispose();
            }
            if (oprot_ != null)
            {
              ((IDisposable)oprot_).Dispose();
            }
          }
        }
        _IsDisposed = true;
      }
      #endregion


      
      #if SILVERLIGHT
      public IAsyncResult Begin_update(AsyncCallback callback, object state, Notification notification)
      {
        return send_update(callback, state, notification);
      }

      public void End_update(IAsyncResult asyncResult)
      {
        oprot_.Transport.EndFlush(asyncResult);
      }

      #endif

      public void update(Notification notification)
      {
        #if !SILVERLIGHT
        send_update(notification);

        #else
        var asyncResult = Begin_update(null, null, notification);

        #endif
      }
      #if SILVERLIGHT
      public IAsyncResult send_update(AsyncCallback callback, object state, Notification notification)
      #else
      public void send_update(Notification notification)
      #endif
      {
        oprot_.WriteMessageBegin(new TMessage("update", TMessageType.Oneway, seqid_));
        update_args args = new update_args();
        args.Notification = notification;
        args.Write(oprot_);
        oprot_.WriteMessageEnd();
        #if SILVERLIGHT
        return oprot_.Transport.BeginFlush(callback, state);
        #else
        oprot_.Transport.Flush();
        #endif
      }

    }
    public class Processor : TProcessor {
      public Processor(ISync iface)
      {
        iface_ = iface;
        processMap_["update"] = update_Process;
      }

      protected delegate void ProcessFunction(int seqid, TProtocol iprot, TProtocol oprot);
      private ISync iface_;
      protected Dictionary<string, ProcessFunction> processMap_ = new Dictionary<string, ProcessFunction>();

      public bool Process(TProtocol iprot, TProtocol oprot)
      {
        try
        {
          TMessage msg = iprot.ReadMessageBegin();
          ProcessFunction fn;
          processMap_.TryGetValue(msg.Name, out fn);
          if (fn == null) {
            TProtocolUtil.Skip(iprot, TType.Struct);
            iprot.ReadMessageEnd();
            TApplicationException x = new TApplicationException (TApplicationException.ExceptionType.UnknownMethod, "Invalid method name: '" + msg.Name + "'");
            oprot.WriteMessageBegin(new TMessage(msg.Name, TMessageType.Exception, msg.SeqID));
            x.Write(oprot);
            oprot.WriteMessageEnd();
            oprot.Transport.Flush();
            return true;
          }
          fn(msg.SeqID, iprot, oprot);
        }
        catch (IOException)
        {
          return false;
        }
        return true;
      }

      public void update_Process(int seqid, TProtocol iprot, TProtocol oprot)
      {
        update_args args = new update_args();
        args.Read(iprot);
        iprot.ReadMessageEnd();
        try
        {
          iface_.update(args.Notification);
        }
        catch (TTransportException)
        {
          throw;
        }
        catch (Exception ex)
        {
          Console.Error.WriteLine("Error occurred in processor:");
          Console.Error.WriteLine(ex.ToString());
        }
      }

    }


    #if !SILVERLIGHT
    [Serializable]
    #endif
    public partial class update_args : TBase
    {
      private Notification _notification;

      /// <summary>
      /// 
      /// <seealso cref="Notification"/>
      /// </summary>
      public Notification Notification
      {
        get
        {
          return _notification;
        }
        set
        {
          __isset.notification = true;
          this._notification = value;
        }
      }


      public Isset __isset;
      #if !SILVERLIGHT
      [Serializable]
      #endif
      public struct Isset {
        public bool notification;
      }

      public update_args() {
      }

      public void Read (TProtocol iprot)
      {
        iprot.IncrementRecursionDepth();
        try
        {
          TField field;
          iprot.ReadStructBegin();
          while (true)
          {
            field = iprot.ReadFieldBegin();
            if (field.Type == TType.Stop) { 
              break;
            }
            switch (field.ID)
            {
              case 1:
                if (field.Type == TType.I32) {
                  Notification = (Notification)iprot.ReadI32();
                } else { 
                  TProtocolUtil.Skip(iprot, field.Type);
                }
                break;
              default: 
                TProtocolUtil.Skip(iprot, field.Type);
                break;
            }
            iprot.ReadFieldEnd();
          }
          iprot.ReadStructEnd();
        }
        finally
        {
          iprot.DecrementRecursionDepth();
        }
      }

      public void Write(TProtocol oprot) {
        oprot.IncrementRecursionDepth();
        try
        {
          TStruct struc = new TStruct("update_args");
          oprot.WriteStructBegin(struc);
          TField field = new TField();
          if (__isset.notification) {
            field.Name = "notification";
            field.Type = TType.I32;
            field.ID = 1;
            oprot.WriteFieldBegin(field);
            oprot.WriteI32((int)Notification);
            oprot.WriteFieldEnd();
          }
          oprot.WriteFieldStop();
          oprot.WriteStructEnd();
        }
        finally
        {
          oprot.DecrementRecursionDepth();
        }
      }

      public override string ToString() {
        StringBuilder __sb = new StringBuilder("update_args(");
        bool __first = true;
        if (__isset.notification) {
          if(!__first) { __sb.Append(", "); }
          __first = false;
          __sb.Append("Notification: ");
          __sb.Append(Notification);
        }
        __sb.Append(")");
        return __sb.ToString();
      }

    }

  }
}
