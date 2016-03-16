package com.adriencadet.downthere;

import com.adriencadet.downthere.models.bll.BLLFactory;
import com.adriencadet.downthere.models.bll.jobs.BLLJobsFactory;
import com.adriencadet.downthere.models.bll.serializers.BLLSerializerFactory;
import com.adriencadet.downthere.models.dao.DAOFactory;
import com.adriencadet.downthere.models.downthereserver.DownthereServerAPIFactory;
import com.adriencadet.downthere.models.downthereserver.DownthereServerFactory;
import com.adriencadet.downthere.models.downthereserver.jobs.DownthereServerJobFactory;
import com.adriencadet.downthere.ui.EventBusFactory;
import com.adriencadet.downthere.ui.activities.BaseActivity;
import com.adriencadet.downthere.ui.adapters.PictureGridAdapter;
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
    DownthereServerAPIFactory.class,
    DownthereServerJobFactory.class,
    BLLSerializerFactory.class,
    BLLJobsFactory.class
})
public interface ApplicationComponent {
    void inject(DownthereApplication application);

    void inject(BaseActivity activity);

    void inject(PictureGridAdapter pictureGridAdapter);

    void inject(BaseFragment fragment);
}
