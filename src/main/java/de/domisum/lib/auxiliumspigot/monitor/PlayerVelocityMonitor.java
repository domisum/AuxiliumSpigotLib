package de.domisum.lib.auxiliumspigot.monitor;

import de.domisum.lib.auxilium.util.java.annotations.APIUsage;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

@APIUsage
public class PlayerVelocityMonitor
{

	// CONSTANTS
	private static final int TIMEOUT_DURATION_MS = 20*1000;

	// REFERENCES
	private Player player;

	// STATUS
	private boolean terminated = false;

	private Vector playerVelocity = new Vector(0, 0, 0);
	private long lastTick;
	private long lastMove;

	private long lastCheck = System.currentTimeMillis();


	// INIT
	protected PlayerVelocityMonitor(Player player)
	{
		this.player = player;
	}

	@APIUsage public void terminate()
	{
		this.terminated = true;
	}


	// GETTERS
	@APIUsage public boolean isTooYoung()
	{
		return (this.lastTick == 0) && (this.lastMove == 0);
	}

	@APIUsage public Vector getVelocity()
	{
		this.lastCheck = System.currentTimeMillis();

		return this.playerVelocity.clone();
	}

	protected Player getPlayer()
	{
		return this.player;
	}

	@APIUsage public boolean isTerminated()
	{
		return this.terminated;
	}

	@APIUsage public boolean isTimedOut()
	{
		return (this.lastCheck+TIMEOUT_DURATION_MS) < System.currentTimeMillis();
	}


	private long getDurationSinceLastMoveMs()
	{
		return System.currentTimeMillis()-this.lastMove;
	}


	// UPDATE
	protected void updatePlayerVelocity(Vector velocity)
	{
		this.playerVelocity = velocity;
		this.lastMove = System.currentTimeMillis();
	}

	protected void tick()
	{
		this.lastTick = System.currentTimeMillis();

		if(getDurationSinceLastMoveMs() > 100)
			this.playerVelocity.multiply(0);
	}

}
