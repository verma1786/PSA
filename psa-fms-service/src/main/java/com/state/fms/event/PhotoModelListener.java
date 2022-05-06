package com.state.fms.event;

import com.state.fms.model.Photo;
import com.state.fms.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

    @Component
    public class PhotoModelListener extends AbstractMongoEventListener<Photo> {
        private SequenceGeneratorService sequenceGenerator;

        @Autowired
        public PhotoModelListener(SequenceGeneratorService sequenceGenerator) {
            this.sequenceGenerator = sequenceGenerator;
        }

        @Override
        public void onBeforeConvert(BeforeConvertEvent<Photo> event) {
            if (event.getSource().getId() < 1) {
                event.getSource().setId(sequenceGenerator.getNextSequenceId(Photo.PHOTO_SEQ_KEY));
            }
        }


    }


