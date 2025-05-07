package lavachu;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.UUID;

public class LavaDeathLogger extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("LavaDeathLogger 플러그인이 활성화되었습니다.");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        getLogger().info("플레이어 사망 감지됨: " + event.getEntity().getName());

        EntityDamageEvent lastDamage = event.getEntity().getLastDamageCause();
        if (lastDamage == null) return;

        getLogger().info("최종 데미지 종류: " + lastDamage.getCause());

        if (lastDamage.getCause() == EntityDamageEvent.DamageCause.LAVA) {
            getLogger().info("용암 사망 - 로그 유지");
            return;
        }

        UUID uuid = event.getEntity().getUniqueId();
        File logsFolder = new File(getDataFolder().getParentFile(), "InventoryRollbackPlus/backups/deaths/" + uuid);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!logsFolder.exists() || !logsFolder.isDirectory()) {
                    getLogger().info("로그 폴더가 존재하지 않음: " + logsFolder.getAbsolutePath());
                    return;
                }

                File[] files = logsFolder.listFiles((dir, name) -> name.endsWith(".yml"));
                if (files == null || files.length == 0) {
                    getLogger().info("삭제할 로그 없음");
                    return;
                }

                for (File file : files) {
                    if (file.delete()) {
                        getLogger().info("삭제됨: " + file.getName());
                    } else {
                        getLogger().warning("삭제 실패: " + file.getName());
                    }
                }
            }
        }.runTaskLater(this, 40L); // 2초(40 ticks) 후 실행
    }
}
