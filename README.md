Ping Status Interface
---------------------
Usage:

    import net.progeny.uswdss.rm.status.PingStatusInterface;

    PingStatusInterface psi = new PingStatusInterface("host");

    psi.refreshStatus();  // ping host, refresh status
    psi.getStatus();      // return Status
