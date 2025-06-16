## Scholarship Management System

### 📁 Repository: `scholarship-management-api`

### 🎯 Product Requirement:

Sistem manajemen beasiswa dan pendaftaran mahasiswa.

### 📡 Endpoint (Minimal 8):

| Method | Endpoint                        | Description            |
| ------ | ------------------------------- | ---------------------- |
| POST   | `/scholarships`                 | Tambah beasiswa        |
| GET    | `/scholarships`                 | List beasiswa          |
| POST   | `/applicants`                   | Daftar beasiswa        |
| GET    | `/applicants`                   | List pendaftar         |
| GET    | `/applicants/{id}`              | Detail pendaftar       |
| PUT    | `/applicants/{id}`              | Update pendaftar       |
| DELETE | `/applicants/{id}`              | Hapus pendaftar        |
| GET    | `/scholarships/{id}/applicants` | Pendaftar per beasiswa |

### 🧪 Validasi:

- `@DecimalMin("0.00")`, `@DecimalMax("4.00")` → GPA
- `@Size(min=3)` → name
- Custom: `open_date < close_date`

---
