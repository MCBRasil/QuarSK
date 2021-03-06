package fr.syst3ms.quarsk.events;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import fr.syst3ms.quarsk.Quarsk;
import fr.syst3ms.quarsk.util.ListUtils;
import org.bukkit.event.Event;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by ARTHUR on 21/02/2017.
 */
@SuppressWarnings({"unused", "unchecked"})
public class PotionSplashSkriptEvent extends SkriptEvent {
    private Literal<PotionEffectType> effectTypesLiteral;

    static {
        Quarsk.newEvent("Potion splashing event", "potion splashing", PotionSplashSkriptEvent.class, PotionSplashEvent.class, "[potion] splash[ing] [(of|with) %-*potioneffecttypes%]");
    }

    @Override
    public boolean init(Literal<?>[] literals, int i, SkriptParser.ParseResult parseResult) {
        effectTypesLiteral = (Literal<PotionEffectType>) literals[0];
        return true;
    }

    @Override
    public boolean check(Event e) {
        if (e instanceof PotionSplashEvent) {
            if (effectTypesLiteral != null) {
                if (effectTypesLiteral.getAll().length > 0 && effectTypesLiteral.getAll() != null) {
                    return Stream.of(effectTypesLiteral.getAll()).allMatch(t ->
                            ListUtils.mapList(new ArrayList<>(((PotionSplashEvent) e).getEntity().getEffects()), PotionEffect::getType).contains(t));
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString(Event event, boolean b) {
        return getClass().getName();
    }
}
