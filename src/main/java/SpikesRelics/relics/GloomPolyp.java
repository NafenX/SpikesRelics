package SpikesRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.NoxiousFumesPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class GloomPolyp extends CustomRelic {
    public static final String ID = "GloomPolyp";
    private static final String IMG = "images/relics/GloomPolyp.png";
    private static final String OUTLINE = "images/relics/outline/GloomPolyp.png";

    public GloomPolyp() {
        super(ID, new Texture(Gdx.files.internal(IMG)), new Texture(Gdx.files.internal(OUTLINE)), RelicTier.BOSS, LandingSound.HEAVY);
    }

    @Override
    public void atBattleStart() {
            this.counter = 0;
            flash();
    }

    public void onEquip() {
        this.counter = 0;
    }

    public void atTurnStart() {
        if (this.counter == -1) {
            this.counter += 2;
        } else {
            ++this.counter;
        }

        if (this.counter == 3) {
            this.counter = 1;
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new NoxiousFumesPower(AbstractDungeon.player, 2), 2));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DESCRIPTIONS[1] + DESCRIPTIONS[2];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new GloomPolyp();
    }
}