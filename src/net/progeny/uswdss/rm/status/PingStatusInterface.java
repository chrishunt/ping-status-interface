package net.progeny.uswdss.rm.status;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import net.progeny.uswdss.rm.status.Status;

public class PingStatusInterface{

  /** Ping timout for this Ping Status Interface */
  private int PING_TIMEOUT = 2000;

  /** Host for this Ping Status Interface */
  private String host;

  /** Status for this Ping Status Interface */
  private Status status;

  /** Construct new Ping Status Interface
   *
   * @param host hostname for this ping status interface
   */
  public PingStatusInterface(String host){
    setHost(host);
    setStatus(Status.UNKNOWN);
  }

  /** Set host for this Ping Status Interface
   *
   * @param host hostname for this ping status interface
   */
  public void setHost(String host){
    this.host = host;
  }

  /** Get host for this Ping Status Interface
   *
   * @return host for this Ping Status Interface
   */
  public String getHost(){
    return this.host;
  }

  /** Set status for this Ping Status Interface
   *
   * @param status status for this ping status interface
   */
  public void setStatus(Status status){
    this.status = status;
  }

  /** Return ping status of this host
   *
   * @return  ping status of this host
   */
  public Status getStatus(){
    return this.status;
  }

  /** Refresh ping status for this Ping Status Interface
   *  
   *  @return true if ping status refreshed successfully
   */
  public boolean refreshStatus(){
    try{
      InetAddress ip = InetAddress.getByName(this.host);
      if (ip.isReachable(PING_TIMEOUT)){
        this.status = Status.RUNNING;
      } else {
        this.status = Status.STOPPED;
      }
      return true;
    } catch (Exception e){
      this.status = Status.UNKNOWN;
      return false;
    }
  }

  public static void main(String[] args){
    String host = "google.com";
    if(args.length > 0)
      host = args[0];

    PingStatusInterface psi = new PingStatusInterface(host);

    psi.refreshStatus();
    System.out.println(psi.getHost() + "(" +  psi.getStatus() + ")");

    System.exit(0);
  }
}
