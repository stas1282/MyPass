package com.example.safekeep;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/example/safekeep/PasswordActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/example/safekeep/ui/adapter/PasswordAdapter;", "currentType", "", "emptyView", "Landroid/widget/TextView;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "tabLayout", "Lcom/google/android/material/tabs/TabLayout;", "viewModel", "Lcom/example/safekeep/ui/viewmodel/PasswordViewModel;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "showAddDialog", "app_debug"})
public final class PasswordActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.safekeep.ui.viewmodel.PasswordViewModel viewModel;
    private com.example.safekeep.ui.adapter.PasswordAdapter adapter;
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private android.widget.TextView emptyView;
    private com.google.android.material.tabs.TabLayout tabLayout;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String currentType = "SITE";
    
    public PasswordActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void showAddDialog() {
    }
}