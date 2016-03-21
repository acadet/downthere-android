package com.adriencadet.downthere;

import com.adriencadet.downthere.models.bll.BLLFactory;
import com.adriencadet.downthere.models.bll.jobs.BLLJobsFactory;
import com.adriencadet.downthere.models.bll.serializers.BLLSerializerFactory;
import com.adriencadet.downthere.models.dao.DAOFactory;
import com.adriencadet.downthere.models.services.downthereserver.DownthereServerFactory;
import com.adriencadet.downthere.models.services.downthereserver.api.DownthereAPIFactory;
import com.adriencadet.downthere.models.services.downthereserver.jobs.DownthereServiceJobFactory;
import com.adriencadet.downthere.ui.events.EventBusFactory;
import com.adriencadet.downthere.ui.activities.BaseActivity;
import com.adriencadet.downthere.ui.adapters.PictureGridAdapter;
import com.adriencadet.downthere.ui.adapters.TextFileListAdapter;
import com.adriencadet.downthere.ui.fragments.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * ApplicationComponent
 * <p>
 */
@Singleton
@Component(modules = {
    ApplicationModule.class,
    BLLFactory.class,
    DAOFactory.class,
    DownthereServerFactory.class,
    EventBusFactory.class,
    DownthereAPIFactory.class,
    DownthereServiceJobFactory.class,
    BLLSerializerFactory.class,
    BLLJobsFactory.class
})
public interface ApplicationComponent {
    void inject(BaseActivity activity);

    void inject(PictureGridAdapter pictureGridAdapter);

    void inject(TextFileListAdapter pictureGridAdapter);

    void inject(BaseFragment fragment);
}
