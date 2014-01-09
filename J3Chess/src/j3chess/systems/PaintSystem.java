package j3chess.systems;

import j3chess.DrawPanel;
import j3chess.J3ChessApp;
import j3chess.components.Paintable;
import j3chess.components.Position;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import artemis.Aspect;
import artemis.ComponentMapper;
import artemis.Entity;
import artemis.annotations.Mapper;
import artemis.systems.EntityProcessingSystem;

public class PaintSystem extends EntityProcessingSystem {

    @Mapper
    private ComponentMapper<Paintable> mPaintComponents;

    @Mapper
    private ComponentMapper<Position> mPosComponents;

    public PaintSystem() {
		super(Aspect.getAspectForAll(Position.class));
	}

	@Override
	protected void process(Entity e) {
		Paintable paint = mPaintComponents.get(e);
		Position pos = mPosComponents.get(e);

		Image img = paint.getImage();

		// TODO sensible image drawing code
		if (img != null) {
			J3ChessApp.getInstance().getDrawGraphics().drawImage(img, 0, 0, null);
		}
	}

}
