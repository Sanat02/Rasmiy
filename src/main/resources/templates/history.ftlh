<#import "spring.ftl" as spring>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link
            href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
            rel="stylesheet"
    />
    <link rel="stylesheet" href="../../static/css/tailwind.output.css">
    <script
            src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
            defer
    ></script>
    <script src="../../static/js/init-alpine.js"></script>
</head>
<body>
<style>
    .main-div {
        background-color: #fff;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        border-radius: 10px;
        width: 80%;
        max-width: 600px;
        padding: 20px;
        overflow-y: auto;
        max-height: 80vh;
        margin: 20px auto;
    }

    .pdf-history-title {
        text-align: center;
        font-size: 24px;
        font-weight: bold;
        margin-bottom: 20px;
        color: #333;
    }

    .pdf-item {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: #fafafa;
        padding: 15px;
        margin-bottom: 10px;
        border-radius: 5px;
        transition: background-color 0.3s;
    }

    .pdf-item:hover {
        background-color: #e0e0e0;
    }

    .pdf-details {
        flex-grow: 1;
        margin-right: 10px;
    }

    .pdf-title {
        font-size: 18px;
        font-weight: bold;
        color: #555;
    }

    .pdf-date {
        font-size: 14px;
        color: #888;
    }

    .pdf-actions {
        display: flex;
        align-items: center;
    }

    .pdf-action {
        margin-left: 10px;
        text-decoration: none;
        color: #007bff;
        font-size: 16px;
        transition: color 0.3s;
    }

    .pdf-action:hover {
        color: #0056b3;
    }
</style>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js"
        integrity="sha512-GsLlZN/3F2ErC5ifS5QtgpiJtWd43JWSuIgh7mbzZ8zBps+dvLusV+eNQATqgA/HdeKFVgA5v3S/cIrLF7QnIg=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<div
        class="flex h-screen bg-gray-50 dark:bg-gray-900"
        :class="{ 'overflow-hidden': isSideMenuOpen}"
>
    <aside
            class="z-20 flex-shrink-0 hidden w-64 overflow-y-auto bg-white dark:bg-gray-800 md:block"
    >
        <div class="py-4 text-gray-500 dark:text-gray-400">
            <a
                    class="ml-6 text-lg font-bold text-gray-800 dark:text-gray-200"
                    href="#"
            >
                Rasmiy
            </a>
            <ul class="mt-6">
                <li class="relative px-6 py-3">
                    <a
                            class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
                            href="/dashboard"
                    >
                        <svg
                                class="w-5 h-5"
                                aria-hidden="true"
                                fill="none"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                stroke-width="2"
                                viewBox="0 0 24 24"
                                stroke="currentColor"
                        >
                            <path
                                    d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"
                            ></path>
                        </svg>
                        <span class="ml-4"><@spring.message 'documents'/></span>
                    </a>
                </li>
                <li class="relative px-6 py-3">
                    <a
                            class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
                            href="/dashboard/history"
                    >
                        <svg
                                class="w-5 h-5"
                                aria-hidden="true"
                                fill="none"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                stroke-width="2"
                                viewBox="0 0 24 24"
                                stroke="currentColor"
                        >
                            <path
                                    d="M19 11H5m14 0a2 2 0 012 2v6a2 2 0 01-2 2H5a2 2 0 01-2-2v-6a2 2 0 012-2m14 0V9a2 2 0 00-2-2M5 11V9a2 2 0 012-2m0 0V5a2 2 0 012-2h6a2 2 0 012 2v2M7 7h10"
                            ></path>
                        </svg>
                        <span class="ml-4"><@spring.message 'history'/></span>
                    </a>
                </li>
            </ul>
            <ul>
                <li class="relative px-6 py-3">
                    <button
                            class="inline-flex items-center justify-between w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
                            @click="togglePagesMenu"
                            aria-haspopup="true"
                    >
                <span class="inline-flex items-center">
                  <svg
                          class="w-5 h-5"
                          aria-hidden="true"
                          fill="none"
                          stroke-linecap="round"
                          stroke-linejoin="round"
                          stroke-width="2"
                          viewBox="0 0 24 24"
                          stroke="currentColor"
                  >
                    <path
                            d="M4 5a1 1 0 011-1h14a1 1 0 011 1v2a1 1 0 01-1 1H5a1 1 0 01-1-1V5zM4 13a1 1 0 011-1h6a1 1 0 011 1v6a1 1 0 01-1 1H5a1 1 0 01-1-1v-6zM16 13a1 1 0 011-1h2a1 1 0 011 1v6a1 1 0 01-1 1h-2a1 1 0 01-1-1v-6z"
                    ></path>
                  </svg>
                  <a href="/logout"> <span class="ml-4"><@spring.message 'logout'/></span></a>
                </span>

                    </button>

                </li>
            </ul>
        </div>
    </aside>

    <div
            x-show="isSideMenuOpen"

            class="fixed inset-0 z-10 flex items-end bg-black bg-opacity-50 sm:items-center sm:justify-center"
    ></div>
    <aside
            class="fixed inset-y-0 z-20 flex-shrink-0 w-64 mt-16 overflow-y-auto bg-white dark:bg-gray-800 md:hidden"
            x-show="isSideMenuOpen"
            @click.away="closeSideMenu"
            @keydown.escape="closeSideMenu"
    >
        <div class="py-4 text-gray-500 dark:text-gray-400">
            <a
                    class="ml-6 text-lg font-bold text-gray-800 dark:text-gray-200"
                    href="#"
            >
                Rasmiy
            </a>
            <ul class="mt-6">
                <li class="relative px-6 py-3">
                    <a
                            class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
                            href="/dashboard"
                    >
                        <svg
                                class="w-5 h-5"
                                aria-hidden="true"
                                fill="none"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                stroke-width="2"
                                viewBox="0 0 24 24"
                                stroke="currentColor"
                        >
                            <path
                                    d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"
                            ></path>
                        </svg>
                        <span class="ml-4"><@spring.message 'documents'/></span>
                    </a>
                </li>

                <li class="relative px-6 py-3">
                    <a
                            class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
                            href="/dashboard/history"
                    >
                        <svg
                                class="w-5 h-5"
                                aria-hidden="true"
                                fill="none"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                stroke-width="2"
                                viewBox="0 0 24 24"
                                stroke="currentColor"
                        >
                            <path
                                    d="M19 11H5m14 0a2 2 0 012 2v6a2 2 0 01-2 2H5a2 2 0 01-2-2v-6a2 2 0 012-2m14 0V9a2 2 0 00-2-2M5 11V9a2 2 0 012-2m0 0V5a2 2 0 012-2h6a2 2 0 012 2v2M7 7h10"
                            ></path>
                        </svg>
                        <span class="ml-4"><@spring.message 'history'/></span>
                    </a>
                </li>
            </ul>
            <ul>

                <li class="relative px-6 py-3">
                    <button
                            class="inline-flex items-center justify-between w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
                            @click="togglePagesMenu"
                            aria-haspopup="true"
                    >
                <span class="inline-flex items-center">
                  <svg
                          class="w-5 h-5"
                          aria-hidden="true"
                          fill="none"
                          stroke-linecap="round"
                          stroke-linejoin="round"
                          stroke-width="2"
                          viewBox="0 0 24 24"
                          stroke="currentColor"
                  >
                    <path
                            d="M4 5a1 1 0 011-1h14a1 1 0 011 1v2a1 1 0 01-1 1H5a1 1 0 01-1-1V5zM4 13a1 1 0 011-1h6a1 1 0 011 1v6a1 1 0 01-1 1H5a1 1 0 01-1-1v-6zM16 13a1 1 0 011-1h2a1 1 0 011 1v6a1 1 0 01-1 1h-2a1 1 0 01-1-1v-6z"
                    ></path>
                  </svg>
                  <a href="/logout"> <span class="ml-4"><@spring.message 'logout'/></span></a>
                </span>

                    </button>

                </li>
            </ul>
        </div>
    </aside>
    <div class="flex flex-col flex-1 w-full">
        <header class="z-10 py-4 bg-white shadow-md dark:bg-gray-800">
            <div
                    class="container flex items-center justify-between h-full px-6 mx-auto text-purple-600 dark:text-purple-300"
            >
                <button
                        class="p-1 mr-5 -ml-1 rounded-md md:hidden focus:outline-none focus:shadow-outline-purple"
                        @click="toggleSideMenu"
                        aria-label="Menu"
                >
                    <svg
                            class="w-6 h-6"
                            aria-hidden="true"
                            fill="currentColor"
                            viewBox="0 0 20 20"
                    >
                        <path
                                fill-rule="evenodd"
                                d="M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z"
                                clip-rule="evenodd"
                        ></path>
                    </svg>
                </button>
                <div class="flex justify-center flex-1 lg:mr-32">

                </div>
                <ul class="flex items-center flex-shrink-0 space-x-6">
                    <!-- Theme toggler -->
                    <li class="flex">
                        <button
                                class="rounded-md focus:outline-none focus:shadow-outline-purple"
                                @click="toggleTheme"
                                aria-label="Toggle color mode"
                        >
                            <template x-if="!dark">
                                <svg
                                        class="w-5 h-5"
                                        aria-hidden="true"
                                        fill="currentColor"
                                        viewBox="0 0 20 20"
                                >
                                    <path
                                            d="M17.293 13.293A8 8 0 016.707 2.707a8.001 8.001 0 1010.586 10.586z"
                                    ></path>
                                </svg>
                            </template>
                            <template x-if="dark">
                                <svg
                                        class="w-5 h-5"
                                        aria-hidden="true"
                                        fill="currentColor"
                                        viewBox="0 0 20 20"
                                >
                                    <path
                                            fill-rule="evenodd"
                                            d="M10 2a1 1 0 011 1v1a1 1 0 11-2 0V3a1 1 0 011-1zm4 8a4 4 0 11-8 0 4 4 0 018 0zm-.464 4.95l.707.707a1 1 0 001.414-1.414l-.707-.707a1 1 0 00-1.414 1.414zm2.12-10.607a1 1 0 010 1.414l-.706.707a1 1 0 11-1.414-1.414l.707-.707a1 1 0 011.414 0zM17 11a1 1 0 100-2h-1a1 1 0 100 2h1zm-7 4a1 1 0 011 1v1a1 1 0 11-2 0v-1a1 1 0 011-1zM5.05 6.464A1 1 0 106.465 5.05l-.708-.707a1 1 0 00-1.414 1.414l.707.707zm1.414 8.486l-.707.707a1 1 0 01-1.414-1.414l.707-.707a1 1 0 011.414 1.414zM4 11a1 1 0 100-2H3a1 1 0 000 2h1z"
                                            clip-rule="evenodd"
                                    ></path>
                                </svg>
                            </template>
                        </button>
                    </li>
                    <!-- Notifications menu -->
                    <li class="relative">
                        <button
                                class="relative align-middle rounded-md focus:outline-none focus:shadow-outline-purple"
                                @click="toggleNotificationsMenu"
                                @keydown.escape="closeNotificationsMenu"
                                aria-label="Notifications"
                                aria-haspopup="true"
                        >
                            <svg
                                    class="w-5 h-5"
                                    aria-hidden="true"
                                    fill="currentColor"
                                    viewBox="0 0 20 20"
                            >
                                <path
                                        d="M10 2a6 6 0 00-6 6v3.586l-.707.707A1 1 0 004 14h12a1 1 0 00.707-1.707L16 11.586V8a6 6 0 00-6-6zM10 18a3 3 0 01-3-3h6a3 3 0 01-3 3z"
                                ></path>
                            </svg>
                            <!-- Notification badge -->
                            <span
                                    aria-hidden="true"
                                    class="absolute top-0 right-0 inline-block w-3 h-3 transform translate-x-1 -translate-y-1 bg-red-600 border-2 border-white rounded-full dark:border-gray-800"
                            ></span>
                        </button>
                        <template x-if="isNotificationsMenuOpen">
                        </template>
                    </li>

                    <li class="flex">
                        <!-- Language dropdown -->
                        <div class="relative" x-data="{ isOpen: false, language: 'kg' }">
                            <button @click="isOpen = !isOpen"
                                    class="rounded-md flex items-center focus:outline-none focus:shadow-outline-purple"
                                    aria-label="Toggle Language Menu">
                                <!-- Language text -->
                                <span class="mx-2"><@spring.message 'lang'/></span>
                            </button>
                            <!-- Language dropdown menu -->
                            <ul x-show="isOpen" @click.away="isOpen = false"
                                class="absolute right-0 w-28 py-1 mt-2 bg-white border border-gray-200 rounded-md shadow-lg">
                                <li>
                                    <a @click="setLanguage('kg')" href="?lang=en"
                                       class="block px-4 py-2 text-sm text-gray-700 text-center"
                                       x-bind:class="{ 'bg-gray-100': language === 'kg' }">Кыргызча</a>
                                </li>
                                <li>
                                    <a @click="setLanguage('ru')" href="?lang=ru"
                                       class="block px-4 py-2 text-sm text-gray-700 text-center"
                                       x-bind:class="{ 'bg-gray-100': language === 'ru' }">Русский</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
        </header>
        <main class="h-full pb-16 overflow-y-auto">
            <div class="main-div">
                <div class="pdf-history-title">PDF History</div>

                <#list statements as statement>
                    <div class="pdf-item">
                        <div class="pdf-details">
                            <div class="pdf-title">Title:${statement.title}</div>
                            <div class="pdf-date">Uploaded on: ${statement.filledDate}</div>
                        </div>
                        <div class="pdf-actions">
                            <a href="/form/success/${statement.getId()}" class="pdf-action">View</a>
                            <a href="/form/education/delete/${statement.getId()}" class="pdf-action"> DELETE</a>
                        </div>
                    </div>
                </#list>

            </div>
        </main>
    </div>
</div>

</script>
</body>
</html>
